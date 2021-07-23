package com.lcs.service.impl;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lcs.dto.LCSResponseDto;
import com.lcs.dto.LCSValue;
import com.lcs.service.LCSService;

@Service("lcsService")
public class LCSServiceImpl implements LCSService {

	@Override
	public LCSResponseDto findLCS(List<LCSValue> list) {
		LCSResponseDto response = new LCSResponseDto();
		Set<LCSValue> result = new HashSet<>();
		int length = list.size();
		LCSValue value = list.get(0);
		for (int i = 0; i < value.getValue().length(); i++) {
			for (int j = i + 1; j <= value.getValue().length(); j++) {
				String stem = value.getValue().substring(i, j);
				int k = 1;
				for (k = 1; k < length; k++) {
					if (!list.get(k).getValue().contains(stem)) {
						break;
					}
				}
				if (k == length && stem != null && !stem.isEmpty()) {
					result.add(new LCSValue(stem));
				}

			}
		}
		Comparator<LCSValue> compare = new Comparator<LCSValue>() {
			@Override
			public int compare(LCSValue o1, LCSValue o2) {
				return o1.getValue().length() - o2.getValue().length();
			}
		};
		LCSValue max = result.stream().max(compare).get();
		response.setLcs(result.stream().filter(x -> {
			return x.getValue().length() == max.getValue().length();
		}).collect(Collectors.toSet()));
		return response;
	}

}
