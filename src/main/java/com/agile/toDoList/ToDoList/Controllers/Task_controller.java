package com.agile.toDoList.ToDoList.Controllers;

import java.util.List;

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

import com.agile.toDoList.ToDoList.Classes.Task;
import com.agile.toDoList.ToDoList.Services.Task_service;

@RestController
public class Task_controller {

	@Autowired
	Task_service task_service;
	
	@RequestMapping(path="/tasks", produces=MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Task> get_tasks(){
		return task_service.get_tasks();
	}
	
	@GetMapping("/tasks/{id}")
	public Task get_task(@PathVariable int id) {
		return task_service.get_task(id);
	}
	
	@GetMapping("/tasks/ordered")
	public List<Task> get_ordered_tasks(){
		return task_service.get_ordered_tasks();
	}
	
	@PostMapping("/tasks")
	public Task add_task(@RequestBody Task task) {
		return task_service.add_task(task);
	}
	
	@PutMapping("/tasks/{id}")
	public Task update_task(@PathVariable int id,@RequestBody Task task) {
		return task_service.update_task(id, task);
	}
	
	@DeleteMapping("tasks/{id}")
	public void delete_task(@PathVariable int id) {
		task_service.delete_task(id);
	}
}
