package com.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.dto.ResponseDto;
import com.assignment.dto.TradeDto;
import com.assignment.service.TradeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author Shruti.Bodhe
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
public class TradeController {

	@Autowired
	private TradeService tradeService;

	// Save single trade
	@PostMapping("/trade")
	public ResponseEntity<ResponseDto> validateAndStoreTrade(@RequestBody TradeDto tradeDto) {

		return new ResponseEntity<>(tradeService.validateAndStoreTrade(tradeDto), HttpStatus.OK);

	}

	// Retrieve trade details with pagination
	@GetMapping("/trade")
	@ApiOperation(value = "Retrieve trades with pagination")
	public ResponseEntity<ResponseDto> getTrades(
			@ApiParam(value = "Page Number") @RequestParam(value = "page", required = false) Integer pageIndex,
			@ApiParam(value = "Limit") @RequestParam(value = "limit", required = false) Integer limit) {
		Pageable pageable;
		if (!(pageIndex != null && limit != null)) {
			pageable = PageRequest.of(0, Integer.MAX_VALUE);
		} else {
			pageable = PageRequest.of(pageIndex, limit);
		}
		return new ResponseEntity<>(tradeService.getTrades(pageable), HttpStatus.OK);

	}

}
