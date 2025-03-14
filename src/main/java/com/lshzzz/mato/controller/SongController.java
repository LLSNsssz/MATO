package com.lshzzz.mato.controller;

import com.lshzzz.mato.model.song.dto.SongRequestDto;
import com.lshzzz.mato.model.song.dto.SongResponseDto;
import com.lshzzz.mato.service.SongService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/songs")
@RequiredArgsConstructor
public class SongController {

	private final SongService songService;

	// 노래 등록
	@PostMapping
	public ResponseEntity<SongResponseDto> createSong(@Valid @RequestBody SongRequestDto requestDto) {
		return ResponseEntity.ok(songService.createSong(requestDto));
	}

	// 모든 노래 조회
	@GetMapping
	public ResponseEntity<List<SongResponseDto>> getAllSongs() {
		return ResponseEntity.ok(songService.getAllSongs());
	}

	// 특정 노래 조회
	@GetMapping("/{id}")
	public ResponseEntity<Optional<SongResponseDto>> getSong(@PathVariable Long id) {
		return ResponseEntity.ok(songService.getSong(id));
	}

	// 노래 수정
	@PutMapping("/{id}")
	public ResponseEntity<SongResponseDto> updateSong(@PathVariable Long id, @Valid @RequestBody SongRequestDto requestDto) {
		return ResponseEntity.ok(songService.updateSong(id, requestDto));
	}

	// 노래 삭제
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
		songService.deleteSong(id);
		return ResponseEntity.noContent().build();
	}
}
