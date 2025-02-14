package com.lshzzz.mato.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class RoomWebSocketController {

	@MessageMapping("/join")
	@SendTo("/topic/room")
	public String joinRoom(String roomName) {
		log.info("User joined room: {}", roomName);
		return "User joined room: " + roomName;
	}

	@MessageMapping("/leave")
	@SendTo("/topic/room")
	public String leaveRoom(String roomName) {
		log.info("User left room: {}", roomName);
		return "User left room: " + roomName;
	}

}
