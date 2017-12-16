package com.agile.toDoList.ToDoList.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class Priority_not_found_exception extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Priority_not_found_exception(int id) {
		super("Prioridad con id = "+id+" no fue encontrada");
	}
}
