package com.lshzzz.mato.model.Map.dto;

import java.time.LocalDateTime;

public record MapResponseDto(
	Long id,
	String name,
	String description,
	Boolean isPublic,
	LocalDateTime createdAt
) {}