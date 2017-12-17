package com.agile.toDoList.ToDoList.Controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agile.toDoList.ToDoList.Classes.Priority;
import com.agile.toDoList.ToDoList.Classes.Task;
import com.agile.toDoList.ToDoList.Services.Priority_service;

@CrossOrigin
@RestController
public class Priority_controller {

	@Autowired
	Priority_service priority_service;
	
	@RequestMapping(path="/priority",produces = MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.GET)
	public Iterable<Priority> get_priorities(){
		return priority_service.get_priorities();
	}
	
	@PostMapping("/priority")
	public Priority add_priority(@RequestBody Priority priority) {
		return priority_service.add_prioriy(priority);
	}
	
	@GetMapping("/priority/{id}")
	public Priority get_priority(@PathVariable int id) {
		return priority_service.get_priority(id);
	}
	
	@GetMapping("/priority/{id}/tasks")
	public Set<Task> get_priority_tasks(@PathVariable int id) {
		return priority_service.get_priority_tasks(id);
	}
	
	@PutMapping("/priority/{id}")
	public Priority update_priority(@PathVariable int id, @RequestBody Priority priority) {
		return priority_service.update_priority(id, priority);
	}
	
	@DeleteMapping("/priority/{id}")
	public boolean delete_priority(@PathVariable int id) {
		return priority_service.delete_repository(id);
	}
}
