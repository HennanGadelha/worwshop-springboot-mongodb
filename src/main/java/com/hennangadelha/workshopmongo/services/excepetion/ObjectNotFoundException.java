package com.hennangadelha.workshopmongo.services.excepetion;

public class ObjectNotFoundException extends RuntimeException {
	static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException (String msg) {
		super(msg);
	}
	
}
