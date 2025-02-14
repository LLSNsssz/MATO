package com.lshzzz.mato.controller;

import com.lshzzz.mato.model.Room;
import com.lshzzz.mato.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {
	private final RoomService roomService;

	// 방 목록 조회
	@GetMapping
	public List<Room> getAllRooms() {
		return roomService.getAllRooms();
	}

	// 방 생성
	@PostMapping
	public Room createRoom(@RequestParam String name) {
		return roomService.createRoom(name);
	}

	// 방 이름으로 조회
	@GetMapping("/search")
	public Room getRoomByName(@RequestParam String name) {
		return roomService.getRoomByName(name);
	}

	// 방 삭제 (방 이름으로 삭제)
	@DeleteMapping("/delete")
	public void deleteRoom(@RequestParam String name) {
		roomService.deleteRoom(name);
	}
}
