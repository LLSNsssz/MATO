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
	// (선택 사항: 만약 SongService를 사용하여 노래를 생성하려면, 해당 서비스도 주입할 수 있음)

	// 맵에 노래 추가 (기존 노래를 맵에 연결하거나, 필요한 경우 새 노래 생성 후 연결)
	public MapSongResponseDto addSongToMap(Long mapId, MapSongRequestDto requestDto) {
		// 1. 맵 엔티티 조회
		Map map = mapRepository.findById(mapId)
			.orElseThrow(() -> new IllegalArgumentException("맵을 찾을 수 없습니다."));

		// 2. 노래 엔티티 결정 (기존 노래 사용 또는 새 노래 생성)
		Song song;
		if (requestDto.songId() != null) {
			// 기존 노래: ID로 조회
			song = songRepository.findById(requestDto.songId())
				.orElseThrow(() -> new IllegalArgumentException("노래를 찾을 수 없습니다."));
		} else if (requestDto.newSong() != null) {
			// 새로운 노래 생성
			Song newSong = Song.builder()
				.title(requestDto.newSong().title()) // 제목 설정
				.artist(requestDto.newSong().artist()) // 아티스트 설정
				.composer(requestDto.newSong().composer()) // 작곡가 설정
				.youtubeUrl(requestDto.newSong().youtubeUrl()) // 유튜브 URL 설정
				.build();
			song = songRepository.save(newSong); // 새 노래 저장
		} else {
			throw new IllegalArgumentException("노래 정보가 제공되지 않았습니다."); // songId 또는 newSong 둘 다 없을 경우 예외 발생
		}

		// 3. 맵과 노래 연결 엔티티 생성 및 저장
		MapSong mapSong = MapSong.builder()
			.map(map) // 맵 설정
			.song(song) // 노래 설정
			.startTime(requestDto.startTime()) // 시작 시간 설정
			.endTime(requestDto.endTime()) // 종료 시간 설정
			.repeatCount(requestDto.repeatCount()) // 반복 횟수 설정
			.build();
		mapSongRepository.save(mapSong); // 저장

		return new MapSongResponseDto(mapSong); // 저장된 정보 반환
	}

	// 특정 맵에 등록된 모든 노래 조회
	@Transactional(readOnly = true)
	public List<MapSongResponseDto> getSongsByMapId(Long mapId) {
		return mapSongRepository.findByMapId(mapId).stream()
			.map(MapSongResponseDto::new)
			.collect(Collectors.toList());
	}

	// 특정 맵에서 노래 삭제 (맵과의 연결 해제)
	public void removeSongFromMap(Long mapSongId) {
		mapSongRepository.deleteById(mapSongId);
	}
}
