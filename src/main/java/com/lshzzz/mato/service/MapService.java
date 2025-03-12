package com.lshzzz.mato.service;

import org.springframework.stereotype.Service;

import com.lshzzz.mato.model.Map.Map;
import com.lshzzz.mato.model.Map.dto.MapRequestDto;
import com.lshzzz.mato.model.Map.dto.MapResponseDto;
import com.lshzzz.mato.repository.MapRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MapService {
	private final MapRepository mapRepository;

	public MapResponseDto createMap(MapRequestDto dto) {
		if (mapRepository.existsByName(dto.name())) {
			throw new IllegalArgumentException("이미 존재하는 맵 이름입니다.");
		}

		Map map = new Map();
		map.setName(dto.name());
		map.setDescription(dto.description());
		map.setIsPublic(dto.isPublic());

		Map savedMap = mapRepository.save(map);
		return new MapResponseDto(
			savedMap.getId(),
			savedMap.getName(),
			savedMap.getDescription(),
			savedMap.getIsPublic(),
			savedMap.getCreatedAt()
		);
	}
}
