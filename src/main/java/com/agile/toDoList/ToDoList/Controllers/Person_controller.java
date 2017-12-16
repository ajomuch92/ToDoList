package com.agile.toDoList.ToDoList.Controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agile.toDoList.ToDoList.Classes.Person;
import com.agile.toDoList.ToDoList.Classes.Task;
import com.agile.toDoList.ToDoList.Services.Person_service;

@RestController
public class Person_controller {
	
	@Autowired
	Person_service person_service;
	
	@RequestMapping(path="/persons", produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Person> Saludar() {
		return person_service.get_persons();
	}
	
	@PostMapping("/persons")
	public Person add_person(@RequestBody Person person) {
		return person_service.add_person(person);
	}
	
	@GetMapping("/persons/{id}")
	public Person get_person(@PathVariable int id) {
		return person_service.get_person(id);
	}
	
	@GetMapping("/persons/{id}/tasks")
	public Set<Task> get_person_tasks(@PathVariable int id) {
		return person_service.get_person_tasks(id);
	}	
	
	@PutMapping("/persons/{id}")
	public Person update_person(@RequestBody Person person, @PathVariable int id) {
		return person_service.update_person(id,person);
	}
	
	@DeleteMapping("/persons/{id}")
	public void delete_person(@PathVariable int id) {
		person_service.delete_person(id);
	}
}
