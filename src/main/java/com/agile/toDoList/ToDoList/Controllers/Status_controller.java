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

import com.agile.toDoList.ToDoList.Classes.Status;
import com.agile.toDoList.ToDoList.Classes.Task;
import com.agile.toDoList.ToDoList.Services.Status_service;

@CrossOrigin
@RestController
public class Status_controller {

	@Autowired
	Status_service status_service;
	
	@RequestMapping(path="/status",produces = MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.GET)
	public Iterable<Status> get_status(){
		return status_service.get_status();
	}
	
	@PostMapping("/status")
	public Status add_status(@RequestBody Status status) {
		return status_service.add_status(status);
	}
	
	@GetMapping("/status/{id}")
	public Status get_one_status(@PathVariable int id) {
		return status_service.get_one_status(id);
	}
	
	@GetMapping("/status/{id}/tasks")
	public Set<Task> get_status_tasks(@PathVariable int id) {
		return status_service.get_status_tasks(id);
	}
	
	@PutMapping("/status/{id}")
	public Status add_status(@RequestBody Status status, @PathVariable int id) {
		return status_service.update_status(id,status);
	}
	
	@DeleteMapping("/status/{id}")
	public boolean delete_status(@PathVariable int id) {
		return status_service.delete_status(id);
	}
}
