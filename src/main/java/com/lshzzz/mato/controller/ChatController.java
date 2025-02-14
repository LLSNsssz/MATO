package com.lshzzz.mato.controller;

import com.lshzzz.mato.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class ChatController {

	@MessageMapping("/send")
	@SendTo("/topic/messages")
	public Message sendMessage(String message) {
		log.info("Received message: {}", message);
		return new Message(message);
	}
}
