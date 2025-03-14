package com.lshzzz.mato.model.map.dto;

import jakarta.validation.constraints.NotNull;

public record MapRequestDto(
	@NotNull Long userId,
	@NotNull String name,
	String description,
	@NotNull Boolean isPublic
) {}