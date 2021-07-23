package com.lcs.controller;

import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lcs.dto.ErrorDto;
import com.lcs.dto.LCSRequestDto;
import com.lcs.dto.LCSValue;
import com.lcs.service.LCSService;

@RestController
public class LCSController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	LCSService lcsService;

	@PostMapping(value = "/lcs")
	public ResponseEntity<?> findLCS(@RequestBody LCSRequestDto lcsRequestDto) {
		if (lcsRequestDto != null && lcsRequestDto.getSetOfStrings() != null
				&& !lcsRequestDto.getSetOfStrings().isEmpty()) {
			HashSet<String> set = new HashSet<>();
			for (LCSValue lcsValue : lcsRequestDto.getSetOfStrings()) {
				if (lcsValue.getValue() == null) {
					return new ResponseEntity<>(new ErrorDto("E1001", "setOfStrings value should be unique."),
							HttpStatus.BAD_REQUEST);
				}
				if (!set.add(lcsValue.getValue())) {
					return new ResponseEntity<>(new ErrorDto("E1001", "setOfStrings value should be unique."),
							HttpStatus.BAD_REQUEST);
				}
			}
			return new ResponseEntity<>(lcsService.findLCS(lcsRequestDto.getSetOfStrings()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorDto("E1002", "setOfStrings should not be empty."),
					HttpStatus.BAD_REQUEST);
		}
	}

	@ExceptionHandler(HttpMessageConversionException.class)
	public final ResponseEntity<?> handleHttpMessageConversionExceptions(HttpMessageConversionException ex) {
		logger.error("HttpMessageConversionException => ", ex);
		return new ResponseEntity<>(new ErrorDto("E1003", "Request is not valid"), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<?> handleAllExceptions(RuntimeException ex) {
		logger.error("RuntimeException => ", ex);
		return new ResponseEntity<>(new ErrorDto("E1004", "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
