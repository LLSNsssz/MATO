package com.lshzzz.mato.service;

import com.lshzzz.mato.model.map.Map;
import com.lshzzz.mato.model.mapsongs.MapSong;
import com.lshzzz.mato.model.mapsongs.dto.MapSongRequestDto;
import com.lshzzz.mato.model.mapsongs.dto.MapSongResponseDto;
import com.lshzzz.mato.model.song.Song;
import com.lshzzz.mato.repository.MapRepository;
import com.lshzzz.mato.repository.MapSongRepository;
import com.lshzzz.mato.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MapSongService {

	private final MapSongRepository mapSongRepository;
	private final MapRepository mapRepository;
	private final SongRepository songRepository;

	// 맵에 노래 추가
	public MapSongResponseDto addSongToMap(Long mapId, MapSongRequestDto requestDto) {
		Map map = mapRepository.findById(mapId)
			.orElseThrow(() -> new IllegalArgumentException("맵을 찾을 수 없습니다."));
		Song song = songRepository.findById(requestDto.songId())
			.orElseThrow(() -> new IllegalArgumentException("노래를 찾을 수 없습니다."));

		MapSong mapSong = MapSong.builder()
			.map(map)
			.song(song)
			.startTime(requestDto.startTime())
			.endTime(requestDto.endTime())
			.repeatCount(requestDto.repeatCount())
			.build();

		mapSongRepository.save(mapSong);
		return new MapSongResponseDto(mapSong);
	}

	// 특정 맵에 등록된 노래 조회
	@Transactional(readOnly = true)
	public List<MapSongResponseDto> getSongsByMapId(Long mapId) {
		return mapSongRepository.findByMapId(mapId)
			.stream()
			.map(MapSongResponseDto::new)
			.collect(Collectors.toList());
	}

	// 특정 맵의 노래 삭제
	public void removeSongFromMap(Long mapSongId) {
		mapSongRepository.deleteById(mapSongId);
	}
}
