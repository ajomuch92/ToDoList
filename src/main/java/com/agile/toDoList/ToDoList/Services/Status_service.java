package com.agile.toDoList.ToDoList.Services;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agile.toDoList.ToDoList.Classes.Status;
import com.agile.toDoList.ToDoList.Classes.Task;
import com.agile.toDoList.ToDoList.Exceptions.Delete_not_allowed_exception;
import com.agile.toDoList.ToDoList.Exceptions.Status_not_found_exception;
import com.agile.toDoList.ToDoList.Repositories.Status_repository;

@Service
public class Status_service {

	@Autowired
	Status_repository status_repository;
	
	private static final Logger logger=Logger.getLogger(Status_service.class);
	
	public Iterable<Status> get_status() {
		logger.info("Getting all the status");
		return status_repository.findAll();
	}

	public Status add_status(Status status) {
		logger.info("Adding a status with named "+status.getStatus_name());
		return status_repository.save(status);
	}

	public Status get_one_status(int id) {
		if(!status_repository.findById(id).isPresent()) {
			logger.error("Status with id="+id+" is not found");
			throw new Status_not_found_exception(id);
		}
		logger.info("Getting one status with id="+id);
		return status_repository.findOne(id);
	}

	public Status update_status(int id, Status status) {
		if(!status_repository.findById(id).isPresent()) {
			logger.error("Status with id="+id+" is not found");
			throw new Status_not_found_exception(id);
		}
		status.setId(id);
		status.setTasks(status_repository.findOne(id).getTasks());
		logger.info("Updating status with id="+id);
		return status_repository.save(status);
	}

	public boolean delete_status(int id) {
		if(!status_repository.findById(id).isPresent()) {
			logger.error("Status with id="+id+" is not found");
			throw new Status_not_found_exception(id);
		}
		if(!status_repository.findOne(id).getTasks().isEmpty()) {
			logger.error("Can't delete the status("+id+") because it has dependecies");
			throw new Delete_not_allowed_exception("You can't delete the status with id="+id+"(Integrity referential)");
		}
		logger.warn("Deleting status with id="+id);
		status_repository.delete(id);
		return true;
	}

	public Set<Task> get_status_tasks(int id) {
		if(!status_repository.findById(id).isPresent()) {
			logger.error("Status with id="+id+" is not found");
			throw new Status_not_found_exception(id);
		}
		return status_repository.findById(id).get().getTasks();
	}

}
