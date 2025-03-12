package com.lshzzz.mato.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lshzzz.mato.model.Map.Map;

@Repository
public interface MapRepository extends JpaRepository<Map, Long> {
	boolean existsByName(String name);

	// 맵 목록을 생성 날짜 기준 내림차순으로 가져오기
	List<Map> findAllByOrderByCreatedAtDesc();

	List<Map> findByMapStatus(Integer mapStatus);

}
