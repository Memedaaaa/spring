package org.springframework.bean;

import org.springframework.context.ApplicationEvent;
@SuppressWarnings("serial")
public class UserRegisteredEvent extends ApplicationEvent {

	public UserRegisteredEvent(Object source) {
		super(source);
	}
}
