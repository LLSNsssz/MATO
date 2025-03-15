package com.lshzzz.mato.controller;

import com.lshzzz.mato.model.map.dto.MapRequestDto;
import com.lshzzz.mato.model.map.dto.MapResponseDto;
import com.lshzzz.mato.service.MapService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/maps")
@RequiredArgsConstructor
public class MapController {
	private final MapService mapService;

	// 🔹 맵 생성
	@PostMapping
	public ResponseEntity<MapResponseDto> createMap(@RequestBody MapRequestDto requestDto) {
		return ResponseEntity.ok(mapService.createMap(requestDto));
	}

	// 🔹 맵 이름 중복 체크 (예외 던지지 않고 바로 응답)
	@GetMapping("/check")
	public ResponseEntity<Map<String, Boolean>> checkMapExists(@RequestParam String name) {
		boolean exists = mapService.checkDuplicateMap(name);
		return ResponseEntity.ok(Collections.singletonMap("isDuplicate", exists));
	}

	// 🔹 특정 맵 조회
	@GetMapping("/{id}")
	public ResponseEntity<MapResponseDto> getMap(@PathVariable Long id) {
		return ResponseEntity.ok(mapService.getMapById(id));
	}

	// 🔹 공개된 맵 목록 조회
	@GetMapping("/public")
	public ResponseEntity<List<MapResponseDto>> getPublicMaps() {
		return ResponseEntity.ok(mapService.getPublicMaps());
	}

	// 🔹 맵 수정
	@PutMapping("/{id}")
	public ResponseEntity<MapResponseDto> updateMap(@PathVariable Long id, @RequestBody MapRequestDto requestDto) {
		return ResponseEntity.ok(mapService.updateMap(id, requestDto));
	}

	// 🔹 맵 삭제 (❗ `@RequestBody` 대신 `@RequestParam` 사용)
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMap(@PathVariable Long id, @RequestParam Long userId) {
		mapService.deleteMap(id, userId);
		return ResponseEntity.noContent().build();
	}
}
