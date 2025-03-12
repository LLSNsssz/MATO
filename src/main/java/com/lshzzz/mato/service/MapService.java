package com.lshzzz.mato.service;

import java.util.List;

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

	public List<MapResponseDto> getAllMaps() {
		return mapRepository.findAll().stream()
			.map(map -> new MapResponseDto(
				map.getId(), map.getName(), map.getDescription(),
				map.getIsPublic(), map.getCreatedAt()
			))
			.toList();
	}

	public MapResponseDto getMapById(Long id) {
		Map map = mapRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("맵을 찾을 수 없습니다."));

		return new MapResponseDto(
			map.getId(), map.getName(), map.getDescription(),
			map.getIsPublic(), map.getCreatedAt()
		);
	}

	public MapResponseDto updateMap(Long id, MapRequestDto dto) {
		Map map = mapRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("맵을 찾을 수 없습니다."));

		map.setName(dto.name());
		map.setDescription(dto.description());
		map.setIsPublic(dto.isPublic());

		Map updatedMap = mapRepository.save(map);
		return new MapResponseDto(
			updatedMap.getId(), updatedMap.getName(), updatedMap.getDescription(),
			updatedMap.getIsPublic(), updatedMap.getCreatedAt()
		);
	}

	public void deleteMap(Long id) {
		if (!mapRepository.existsById(id)) {
			throw new IllegalArgumentException("삭제할 맵을 찾을 수 없습니다.");
		}
		mapRepository.deleteById(id);
	}

}
