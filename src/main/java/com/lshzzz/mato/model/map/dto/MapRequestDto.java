package com.lshzzz.mato.model.map.dto;

public record MapRequestDto(
	String name,
	String description,
	Boolean isPublic
) {}