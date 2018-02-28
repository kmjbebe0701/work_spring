package com.koitt.test;

import java.util.Locale;

import org.springframework.context.MessageSource;

public class Example {
	
	private MessageSource messages;
	
	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}
	
	public void execute() {
		String message01 = this.messages.getMessage("greeting", null, "Hello", Locale.KOREAN);
		
		// argument.required항목에서 {}안에 Apple 넣고 혹시 항목을 못찾으면 Banana출력 (한국어로)
		String message02 = this.messages.getMessage("argument.required", new Object[] {"Apple"}, "Banana", Locale.KOREAN);
		
		System.out.println(message01);
		System.out.println(message02);

	}

}
