package com.lshzzz.mato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lshzzz.mato.model.Map;

@Repository
public interface MapRepository extends JpaRepository<Map, Long> {
	boolean existsByName(String name);
}
