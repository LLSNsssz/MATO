package com.lshzzz.mato.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Room {
	private String id;
	private String name;

	public Room(String name) {
		this.id = UUID.randomUUID().toString(); // 랜덤한 ID 부여
		this.name = name;
	}
}
