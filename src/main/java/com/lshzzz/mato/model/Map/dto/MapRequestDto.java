package com.lshzzz.mato.model.Map.dto;

public record MapRequestDto(
	String name,
	String description,
	Boolean isPublic
) {}