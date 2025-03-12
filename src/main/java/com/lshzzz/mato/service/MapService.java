package com.lshzzz.mato.service;

import org.springframework.stereotype.Service;

import com.lshzzz.mato.model.Map;
import com.lshzzz.mato.repository.MapRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MapService {
	private final MapRepository mapRepository;

	public Map createMap(String name, String description, Boolean isPublic) {
		if (mapRepository.existsByName(name)) {
			throw new IllegalArgumentException("이미 존재하는 맵 이름입니다.");
		}

		Map map = new Map();
		map.setName(name);
		map.setDescription(description);
		map.setIsPublic(isPublic);
		return mapRepository.save(map);
	}
}
