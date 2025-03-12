package com.lshzzz.mato.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping
	public ResponseEntity<List<MapResponseDto>> getAllMaps() {
		return ResponseEntity.ok(mapService.getAllMaps());
	}

	@GetMapping("/{id}")
	public ResponseEntity<MapResponseDto> getMapById(@PathVariable Long id) {
		return ResponseEntity.ok(mapService.getMapById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<MapResponseDto> updateMap(@PathVariable Long id, @RequestBody MapRequestDto dto) {
		return ResponseEntity.ok(mapService.updateMap(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMap(@PathVariable Long id) {
		mapService.deleteMap(id);
		return ResponseEntity.noContent().build();
	}
}
