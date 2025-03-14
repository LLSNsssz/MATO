package com.lshzzz.mato.service;

import com.lshzzz.mato.model.map.Map;
import com.lshzzz.mato.model.map.dto.MapRequestDto;
import com.lshzzz.mato.model.map.dto.MapResponseDto;
import com.lshzzz.mato.repository.MapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MapService {
	private final MapRepository mapRepository;

	// 맵 생성
	@Transactional
	public MapResponseDto createMap(MapRequestDto requestDto, Long userId) {
		Map map = Map.builder()
			.userId(userId)
			.name(requestDto.name())
			.description(requestDto.description())
			.isPublic(requestDto.isPublic())
			.build();

		Map savedMap = mapRepository.save(map);
		return new MapResponseDto(savedMap);
	}

	// 특정 맵 조회
	@Transactional(readOnly = true)
	public MapResponseDto getMapById(Long id) {
		Map map = mapRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 ID의 맵이 존재하지 않습니다."));
		return new MapResponseDto(map);
	}

	// 공개된 맵 목록 조회
	@Transactional(readOnly = true)
	public List<MapResponseDto> getPublicMaps() {
		List<Map> maps = mapRepository.findByIsPublicTrue();
		return maps.stream().map(MapResponseDto::new).toList();
	}

	// 맵 정보 수정
	@Transactional
	public MapResponseDto updateMap(Long id, MapRequestDto requestDto, Long userId) {
		Map map = mapRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 ID의 맵이 존재하지 않습니다."));

		if (!map.getUserId().equals(userId)) {
			throw new IllegalArgumentException("맵 수정 권한이 없습니다.");
		}

		map.update(requestDto.name(), requestDto.description(), requestDto.isPublic());
		return new MapResponseDto(map);
	}

	// 맵 삭제
	@Transactional
	public void deleteMap(Long id, Long userId) {
		Map map = mapRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 ID의 맵이 존재하지 않습니다."));

		if (!map.getUserId().equals(userId)) {
			throw new IllegalArgumentException("맵 삭제 권한이 없습니다.");
		}

		mapRepository.delete(map);
	}
}
