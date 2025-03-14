package com.lshzzz.mato.model.mapsongs.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record MapSongRequestDto(
	@NotNull Long songId,     // 노래 ID
	@Min(0) Integer startTime, // 시작 시점 (초 단위)
	@Min(1) Integer endTime,   // 종료 시점 (초 단위)
	@Min(1) Integer repeatCount // 반복 횟수 (최소 1번)
) {}
