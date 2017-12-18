package com.agile.toDoList.ToDoList.Services;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agile.toDoList.ToDoList.Classes.Priority;
import com.agile.toDoList.ToDoList.Classes.Task;
import com.agile.toDoList.ToDoList.Exceptions.Delete_not_allowed_exception;
import com.agile.toDoList.ToDoList.Exceptions.Priority_not_found_exception;
import com.agile.toDoList.ToDoList.Repositories.Priority_repository;

@Service
public class Priority_service {

	@Autowired
	Priority_repository priority_repository;
	
	private static final Logger logger=Logger.getLogger(Priority_service.class);

	public Iterable<Priority> get_priorities() {
		logger.info("Getting all priorities");
		return priority_repository.findAll();
	}

	public Priority add_prioriy(Priority priority) {
		logger.info("Adding priority named "+priority.getName_priority());
		return priority_repository.save(priority);
	}

	public Priority get_priority(int id) {
		if(!priority_repository.findById(id).isPresent()) {
			logger.error("Priority with id="+id+" not found");
			throw new Priority_not_found_exception(id);
		}
		logger.info("Getting one reposity with id="+id);
		return priority_repository.findOne(id);
	}
	
	public Priority update_priority(int id, Priority priority) {
		if(!priority_repository.findById(id).isPresent()) {
			logger.error("Priority with id="+id+" not found");
			throw new Priority_not_found_exception(id);
		}
		priority.setId(id);
		priority.setTasks(priority_repository.findOne(id).getTasks());
		logger.info("Updating one priority with id="+id);
		return priority_repository.save(priority);
	}
	
	public boolean delete_repository(int id) {
		if(!priority_repository.findById(id).isPresent()) {
			logger.error("Priority with id="+id+" not found");
			throw new Priority_not_found_exception(id);
		}
		if(!priority_repository.findOne(id).getTasks().isEmpty()) {
			logger.error("Can't delete the priority("+id+") because it has dependecies");
			throw new Delete_not_allowed_exception("You can't delete the priority with id="+id+"(Integrity referential)");
		}		
		logger.warn("Deleting one priority with id="+id);
		priority_repository.delete(id);
		return true;
	}

	public Set<Task> get_priority_tasks(int id) {
		if(!priority_repository.findById(id).isPresent()) {
			logger.error("Priority with id="+id+" not found");
			throw new Priority_not_found_exception(id);
		}
		return priority_repository.findOne(id).getTasks();
	}
}
