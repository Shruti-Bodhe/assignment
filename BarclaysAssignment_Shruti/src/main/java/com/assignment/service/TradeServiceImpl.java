package com.assignment.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.assignment.dto.ResponseDto;
import com.assignment.dto.TradeDto;
import com.assignment.entity.TradeDo;
import com.assignment.repo.TradeRepository;

/**
 * @author Shruti.Bodhe
 *
 */
@Service
@Transactional
public class TradeServiceImpl implements TradeService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TradeRepository tradeRepository;

	@Override
	public ResponseDto validateAndStoreTrade(TradeDto tradeDto) {
		logger.info("TradeServiceImpl | validateAndStoreTrade | Execution start : Input : tradeDto : " + tradeDto);

		ResponseDto responseDto = new ResponseDto();
		Boolean isTradeValid = Boolean.FALSE;

		try {
			responseDto.setStatus(Boolean.TRUE);
			responseDto.setStatusCode(200);

			// Validate trade
			isTradeValid = validateSingleTrade(tradeDto);
			if (isTradeValid) {

				// save or update trade
				tradeRepository.save(tradeRepository.importDto(tradeDto));

				responseDto.setMessage("Trade stored successfully!");
				responseDto.setData(tradeDto);
			} else {
				responseDto.setStatus(Boolean.FALSE);
				responseDto.setStatusCode(200);
				responseDto.setMessage("Trade is not valid!");
			}

		} catch (Exception e) {
			logger.error("TradeServiceImpl | validateAndStoreTrade | Message : " + responseDto.getMessage());
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());

		}
		logger.info("TradeServiceImpl | validateAndStoreTrade | Execution end :Output : Response  " + responseDto);

		return responseDto;
	}

	@Override
	public ResponseDto getTrades(Pageable pageable) {
		logger.info("TradeServiceImpl | getTrades | Execution start : Input : pageable : " + pageable);

		ResponseDto responseDto = new ResponseDto();
		List<TradeDto> tradeDtos = null;

		try {
			responseDto.setStatus(Boolean.TRUE);
			responseDto.setStatusCode(200);

			Page<TradeDo> tradeEntities = tradeRepository.findAll(pageable);
			if (tradeEntities != null) {
				tradeDtos = tradeEntities.getContent().stream().map(entity -> tradeRepository.exportDto(entity))
						.collect(Collectors.toList());
			}
			responseDto.setData(tradeDtos);
			responseDto.setMessage("Trade stored successfully!");

		} catch (Exception e) {
			logger.error("TradeServiceImpl | getTrades | Message : " + responseDto.getMessage());
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());

		}
		logger.info("TradeServiceImpl | getTrades | Execution end :Output : Response  " + responseDto.getMessage());

		return responseDto;
	}

	public Boolean validateSingleTrade(TradeDto tradeDto) throws Exception {
		logger.info("TradeServiceImpl | validateSingleTrade |  Input : tradeDto : " + tradeDto);

		Boolean isTradeVersionValid = Boolean.FALSE;
		Boolean isMaturityDateValid = Boolean.FALSE;

		// Scenario 1: lower version of trade received - reject trade

		Integer latestVersion = tradeRepository.findLatestVersionByTradeId(tradeDto.getTradeId());
		if (latestVersion == null || (tradeDto.getVersion() != null && latestVersion <= tradeDto.getVersion())) {
			isTradeVersionValid = true;
		} else {
			throw new Exception("Lower version is being received for Trade Id : " + tradeDto.getTradeId());
		}

		// Scenario 2: Trade with less maturity date then today - reject trade
		if (tradeDto.getMaturityDate().after(new Date())) {
			isMaturityDateValid = true;
		} else {
			throw new Exception("Maturity date is less than today's date for trade id : " + tradeDto.getTradeId());
		}

		if (isTradeVersionValid && isMaturityDateValid) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public Integer updateFlagForExpiredTrade(Boolean isExpiredFlag) {
		logger.info("TradeServiceImpl | updateFlagForExpiredTrade | Input : isExpiredFlag : " + isExpiredFlag);

		return tradeRepository.updateIsExpired(isExpiredFlag);

	}

	// Scenario 3 : Run job at midnight - update isExpired flag to true if
	// maturityDate > current_date
	@Scheduled(cron = "* 0 0 * * *")
	public void scheduleTaskUsingCronExpression() {
		logger.info("TradeServiceImpl | scheduleTaskUsingCronExpression | Execution start");

		Integer rowsAffected = updateFlagForExpiredTrade(Boolean.TRUE);

		logger.info(
				"TradeServiceImpl | scheduleTaskUsingCronExpression | Execution end | rowsAffected = " + rowsAffected);
	}

}
