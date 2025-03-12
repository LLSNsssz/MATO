package com.lshzzz.mato.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lshzzz.mato.model.Map.dto.MapRequestDto;
import com.lshzzz.mato.model.Map.dto.MapResponseDto;
import com.lshzzz.mato.service.MapService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/maps")
@RequiredArgsConstructor
public class MapController {
	private final MapService mapService;

	@PostMapping
	public ResponseEntity<MapResponseDto> createMap(@RequestBody MapRequestDto dto) {
		return ResponseEntity.ok(mapService.createMap(dto));
	}
}
