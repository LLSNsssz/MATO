package com.lshzzz.mato.model.song.dto;

import jakarta.validation.constraints.NotBlank;

public record SongRequestDto(
	@NotBlank String title,    // 제목
	@NotBlank String artist,   // 가수
	String composer,           // 작곡가 (옵션)
	@NotBlank String audioUrl  // 오디오 URL (유튜브 링크 OR 업로드된 파일)
) {}
