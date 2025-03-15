package com.lshzzz.mato.service;

import com.lshzzz.mato.model.map.Map;
import com.lshzzz.mato.model.map.dto.MapRequestDto;
import com.lshzzz.mato.model.map.dto.MapResponseDto;
import com.lshzzz.mato.model.mapsongs.dto.MapSongRequestDto;
import com.lshzzz.mato.model.song.dto.SongRequestDto;
import com.lshzzz.mato.model.song.dto.SongResponseDto;
import com.lshzzz.mato.repository.MapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MapService {
	private final MapRepository mapRepository;
	private final SongService songService;
	private final MapSongService mapSongService;

	// 맵 생성
	@Transactional
	public MapResponseDto createMap(MapRequestDto requestDto) {
		// 1. 맵(Map) 엔티티 생성 및 저장
		Map map = Map.builder()
			.userId(requestDto.userId()) // 사용자 ID 설정
			.name(requestDto.name()) // 맵 이름 설정
			.description(requestDto.description()) // 맵 설명 설정
			.isPublic(requestDto.isPublic()) // 공개 여부 설정
			.build();

		Map savedMap = mapRepository.save(map); // 맵 저장

		// 2. 노래 목록이 제공된 경우, 각 노래를 맵에 추가
		List<MapSongRequestDto> songsToAdd = requestDto.songs();
		if (songsToAdd != null) {
			for (MapSongRequestDto songReq : songsToAdd) {
				if (songReq.songId() != null) {
					// 기존에 존재하는 노래일 경우: 맵과 연결만 수행
					mapSongService.addSongToMap(savedMap.getId(), songReq);
				} else if (songReq.newSong() != null) {
					// 새로운 노래일 경우: 먼저 생성한 후 맵과 연결
					SongRequestDto newSongInfo = songReq.newSong();
					SongResponseDto createdSong = songService.createSong(newSongInfo);

					// 생성된 노래의 ID를 가져와서 맵에 추가
					Long newSongId = createdSong.id();
					MapSongRequestDto linkDto = new MapSongRequestDto(
						newSongId,
						null,  // 이미 생성된 노래이므로 SongRequestDto 불필요
						songReq.startTime(), // 시작 시간 설정
						songReq.endTime(), // 종료 시간 설정
						songReq.repeatCount() // 반복 횟수 설정
					);
					mapSongService.addSongToMap(savedMap.getId(), linkDto);
				}
			}
		}
		return new MapResponseDto(savedMap); // 저장된 맵 정보 반환
	}


	// 특정 맵 조회
	@Transactional(readOnly = true)
	public MapResponseDto getMapById(Long id) {
		Map map = mapRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 ID의 맵이 존재하지 않습니다."));
		return new MapResponseDto(map);
	}

	// 공개된 맵 목록 조회
	@Transactional(readOnly = true)
	public List<MapResponseDto> getPublicMaps() {
		List<Map> maps = mapRepository.findByIsPublicTrue();
		return maps.stream().map(MapResponseDto::new).toList();
	}

	// 맵 정보 수정
	public MapResponseDto updateMap(Long id, MapRequestDto requestDto) {
		// 1. 해당 ID의 맵을 조회
		Map map = mapRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 ID의 맵이 존재하지 않습니다."));

		// 2. 맵 소유자만 수정할 수 있도록 검증
		if (!map.getUserId().equals(requestDto.userId())) {
			throw new IllegalArgumentException("맵 수정 권한이 없습니다.");
		}

		// 3. 맵 정보 업데이트 (JPA가 엔티티를 관리하므로 변경 사항 자동 저장)
		map.update(requestDto.name(), requestDto.description(), requestDto.isPublic());

		// 4. 업데이트된 맵 정보 반환
		return new MapResponseDto(map);
	}


	// 맵 삭제
	@Transactional
	public void deleteMap(Long id, Long userId) {
		Map map = mapRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 ID의 맵이 존재하지 않습니다."));

		if (!map.getUserId().equals(userId)) {
			throw new IllegalArgumentException("맵 삭제 권한이 없습니다.");
		}

		mapRepository.delete(map);
	}

	// 맵 이름 중복 체크
	public boolean checkDuplicateMap(String name) {
		return mapRepository.existsByName(name);
	}

	// ✅ 중복 검사 시 예외 발생하도록 변경
	public void validateDuplicateMap(String name) {
		if (mapRepository.existsByName(name)) {
			throw new IllegalArgumentException("이미 존재하는 맵 이름입니다: " + name);
		}
	}
}
