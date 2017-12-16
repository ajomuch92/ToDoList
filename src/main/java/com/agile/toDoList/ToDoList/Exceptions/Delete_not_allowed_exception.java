package com.agile.toDoList.ToDoList.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class Delete_not_allowed_exception extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Delete_not_allowed_exception(String msg) {
		super(msg);
	}
}
