package com.lshzzz.mato.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
	private String content;

	public Message(String content) {
		this.content = content;
	}
}

