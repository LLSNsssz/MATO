package com.lshzzz.mato.model.map.dto;

import java.time.LocalDateTime;

import com.lshzzz.mato.model.map.Map;

public record MapResponseDto(
	Long id,
	String name,
	String description,
	Boolean isPublic,
	LocalDateTime createdAt,
	LocalDateTime updatedAt
) {
	public MapResponseDto(Map map) {
		this(map.getId(), map.getName(), map.getDescription(), map.getIsPublic(),
			map.getCreatedAt(), map.getUpdatedAt());
	}
}