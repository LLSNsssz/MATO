package com.lshzzz.mato.controller;

import com.lshzzz.mato.model.mapsongs.dto.MapSongRequestDto;
import com.lshzzz.mato.model.mapsongs.dto.MapSongResponseDto;
import com.lshzzz.mato.service.MapSongService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mapsongs")
@RequiredArgsConstructor
public class MapSongController {

	private final MapSongService mapSongService;

	// 맵에 노래 추가
	@PostMapping
	public ResponseEntity<MapSongResponseDto> addSongToMap(@Valid @RequestBody MapSongRequestDto requestDto) {
		return ResponseEntity.ok(mapSongService.addSongToMap(requestDto));
	}

	// 특정 맵의 노래 목록 조회
	@GetMapping("/map/{mapId}")
	public ResponseEntity<List<MapSongResponseDto>> getSongsByMapId(@PathVariable Long mapId) {
		return ResponseEntity.ok(mapSongService.getSongsByMapId(mapId));
	}

	// 맵에서 특정 노래 삭제
	@DeleteMapping("/{mapSongId}")
	public ResponseEntity<Void> removeSongFromMap(@PathVariable Long mapSongId) {
		mapSongService.removeSongFromMap(mapSongId);
		return ResponseEntity.noContent().build();
	}
}
