package com.lshzzz.mato.model.mapsongs.dto;

import com.lshzzz.mato.model.mapsongs.MapSong;

import java.time.LocalDateTime;

public record MapSongResponseDto(
	Long id,
	Long mapId,
	Long songId,
	Integer startTime,
	Integer endTime,
	Integer repeatCount,
	LocalDateTime createdAt,
	LocalDateTime updatedAt
) {
	public MapSongResponseDto(MapSong mapSong) {
		this(
			mapSong.getId(),
			mapSong.getMap().getId(),
			mapSong.getSong().getId(),
			mapSong.getStartTime(),
			mapSong.getEndTime(),
			mapSong.getRepeatCount(),
			mapSong.getCreatedAt(),
			mapSong.getUpdatedAt()
		);
	}
}
