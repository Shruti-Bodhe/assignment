package com.assignment.service;

import org.springframework.data.domain.Pageable;

import com.assignment.dto.ResponseDto;
import com.assignment.dto.TradeDto;

/**
 * @author Shruti.Bodhe
 *
 */
public interface TradeService {

	ResponseDto validateAndStoreTrade(TradeDto tradeDto);

	ResponseDto getTrades(Pageable pageable);

}
