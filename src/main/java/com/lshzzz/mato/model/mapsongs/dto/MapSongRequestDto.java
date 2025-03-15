package com.lshzzz.mato.model.mapsongs.dto;

import com.lshzzz.mato.model.song.dto.SongRequestDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record MapSongRequestDto(
	Long songId,     // 노래 ID
	@Valid SongRequestDto newSong, // 새로운 노래 정보 (신규 노래 추가 시 사용, songId는 null로)
	@Min(0) Integer startTime, // 시작 시점 (초 단위)
	@Min(1) Integer endTime,   // 종료 시점 (초 단위)
	@Min(1) Integer repeatCount // 반복 횟수 (최소 1번)
) {}
