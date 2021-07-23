package com.lcs.service;

import java.util.List;

import com.lcs.dto.LCSResponseDto;
import com.lcs.dto.LCSValue;

public interface LCSService {

	LCSResponseDto findLCS(List<LCSValue> list);

}
