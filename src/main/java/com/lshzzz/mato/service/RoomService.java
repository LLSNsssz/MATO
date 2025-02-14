package com.lshzzz.mato.service;

import com.lshzzz.mato.model.Room;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoomService {
	private final Map<String, Room> rooms = new HashMap<>();

	public List<Room> getAllRooms() {
		return new ArrayList<>(rooms.values());
	}

	public Room createRoom(String name) {
		Room room = new Room(name);
		rooms.put(room.getId(), room);
		return room;
	}

	public Room getRoomByName(String name) {
		return rooms.values().stream()
			.filter(room -> room.getName().equals(name))
			.findFirst()
			.orElse(null);
	}

	public void deleteRoom(String name) {
		rooms.values().removeIf(room -> room.getName().equals(name));
	}
}
