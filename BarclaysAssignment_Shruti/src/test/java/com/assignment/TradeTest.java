package com.assignment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import com.assignment.dto.ResponseDto;
import com.assignment.dto.TradeDto;
import com.assignment.service.TradeServiceImpl;

/**
 * @author Shruti.Bodhe
 *
 */
@SpringBootTest
public class TradeTest {

	@Autowired
	private TradeServiceImpl tradeServiceImpl;

	// Scenario 1: lower version of trade received - reject trade
	@Test
	public void testLowerVersionValidation() {
		Date tomorrow = new Date(new Date().getTime() + (1000 * 60 * 60 * 24));

		TradeDto tradeDto = new TradeDto();
		tradeDto.setTradeId("T2");
		tradeDto.setVersion(1);
		tradeDto.setMaturityDate(tomorrow);

		Exception exception = assertThrows(Exception.class, () -> {
			tradeServiceImpl.validateSingleTrade(tradeDto);
		});

		String expectedMessage = "Lower version is being received for Trade Id : T2";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	// Scenario 2: Version is same/higher - record to be saved/updated in db
	@Test
	public void testSameVersionValidatio() {
		Date tomorrow = new Date(new Date().getTime() + (1000 * 60 * 60 * 24));

		TradeDto tradeDto = new TradeDto();
		tradeDto.setTradeId("T2");
		tradeDto.setVersion(2);
		tradeDto.setMaturityDate(tomorrow);

		Boolean isValid = Boolean.FALSE;
		try {
			isValid = tradeServiceImpl.validateSingleTrade(tradeDto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertEquals(Boolean.TRUE, isValid);
	}

	// Scenario 3: Trade with less maturity date then today - reject trade
	@Test
	public void testMaturityDateValidation() {
		Date yesterday = new Date(new Date().getTime() - (1000 * 60 * 60 * 24));

		TradeDto tradeDto = new TradeDto();
		tradeDto.setTradeId("T3");
		tradeDto.setVersion(3);
		tradeDto.setMaturityDate(yesterday);

		Exception exception = assertThrows(Exception.class, () -> {
			tradeServiceImpl.validateSingleTrade(tradeDto);
		});

		String expectedMessage = "Maturity date is less than today's date for trade id : T3";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	// Scenario 4: Trade with less maturity date then today - reject trade
	@Test
	public void testExpirationCheck() {

		Integer rowCount = tradeServiceImpl.updateFlagForExpiredTrade(Boolean.TRUE);

		assertEquals(1, rowCount);
	}

	// Test validate and store trades method
	@Test
	public void testStoreTrade() {
		Date tomorrow = new Date(new Date().getTime() + (1000 * 60 * 60 * 24));
		TradeDto tradeDto = new TradeDto();
		tradeDto.setTradeId("T4");
		tradeDto.setVersion(1);
		tradeDto.setMaturityDate(tomorrow);
		tradeDto.setBookId("B1");
		tradeDto.setCountryPartyId("CP-1");
		tradeDto.setCreatedDate(new Date());
		tradeDto.setExpired(Boolean.FALSE);

		ResponseDto responseDto = tradeServiceImpl.validateAndStoreTrade(tradeDto);

		assertEquals(Boolean.TRUE, responseDto.getStatus());
		assertEquals(200, responseDto.getStatusCode());
		assertEquals("Trade stored successfully!", responseDto.getMessage());
		assertNotNull(responseDto.getData());

	}

	// Test get trades method
	@Test
	@SuppressWarnings("unchecked")
	public void testGetTrade() {

		ResponseDto responseDto = tradeServiceImpl.getTrades(PageRequest.of(0, Integer.MAX_VALUE));
		List<TradeDto> tradeDtos = (List<TradeDto>) responseDto.getData();

		assertThat(tradeDtos).size().isGreaterThan(0);
		assertEquals(Boolean.TRUE, responseDto.getStatus());
		assertEquals(200, responseDto.getStatusCode());
	}

}
