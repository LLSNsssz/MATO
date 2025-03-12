package com.lshzzz.mato.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lshzzz.mato.model.Map;
import com.lshzzz.mato.service.MapService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/maps")
@RequiredArgsConstructor
public class MapController {
	private final MapService mapService;

	@PostMapping
	public ResponseEntity<Map> createMap(@RequestBody Map map) {
		return ResponseEntity.ok(mapService.createMap(map.getName(), map.getDescription(), map.getIsPublic()));
	}
}
