package com.agile.toDoList.ToDoList.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agile.toDoList.ToDoList.Classes.Person;
import com.agile.toDoList.ToDoList.Classes.Priority;
import com.agile.toDoList.ToDoList.Classes.Status;
import com.agile.toDoList.ToDoList.Classes.Task;
import com.agile.toDoList.ToDoList.Exceptions.Person_not_found_exception;
import com.agile.toDoList.ToDoList.Exceptions.Priority_not_found_exception;
import com.agile.toDoList.ToDoList.Exceptions.Save_task_exception;
import com.agile.toDoList.ToDoList.Exceptions.Status_not_found_exception;
import com.agile.toDoList.ToDoList.Exceptions.Task_not_found_exception;
import com.agile.toDoList.ToDoList.Repositories.Person_repository;
import com.agile.toDoList.ToDoList.Repositories.Priority_repository;
import com.agile.toDoList.ToDoList.Repositories.Status_repository;
import com.agile.toDoList.ToDoList.Repositories.Task_repository;

@Service
public class Task_service {

	@Autowired
	Task_repository task_repository;
	@Autowired
	Person_repository person_repository;
	@Autowired
	Status_repository status_repository;
	@Autowired
	Priority_repository priority_repository;
	
	private static final Logger logger=Logger.getLogger(Status_service.class);
	
	public Iterable<Task> get_tasks(){
		logger.info("Getting all task");
		return task_repository.findAll();
	}
	
	public Task add_task(Task task) {
		return this.save_task(task);
	}
	
	public Task get_task(int id) {
		if(!task_repository.findById(id).isPresent()) {
			logger.error("Task with id="+id+" not found");
			throw new Task_not_found_exception(id);
		}
		return task_repository.findOne(id);
	}
	
	public Task update_task(int id,Task task) {
		if(!task_repository.findById(id).isPresent()) {
			logger.error("Task with id="+id+" not found");
			throw new Task_not_found_exception(id);
		}
		task.setId(id);
		return this.save_task(task);
	}
	
	public void delete_task(int id) {
		if(!task_repository.findById(id).isPresent()) {
			logger.error("Task with id="+id+" not found");
			throw new Task_not_found_exception(id);
		}
		task_repository.delete(id);
	}
	
	private Task save_task(Task task) {
		try {
			if(!person_repository.findById(task.getPerson().getId()).isPresent()) {
				logger.error("The person name "+task.getPerson().getName()+" is not found");
				throw new Person_not_found_exception(task.getPerson().getId());
			}
			if(!status_repository.findById(task.getStatus().getId()).isPresent()) {
				logger.error("The status named "+task.getStatus().getStatus_name()+" is not found");
				throw new Status_not_found_exception(task.getStatus().getId());
			}
			if(!priority_repository.findById(task.getPriority().getId()).isPresent()) {
				logger.error("The priority named "+task.getPriority().getName_priority()+" is not found");
				throw new Priority_not_found_exception(task.getPriority().getId());
			}
			Person p=person_repository.findOne(task.getPerson().getId());
			Status s=status_repository.findOne(task.getStatus().getId());
			Priority r=priority_repository.findOne(task.getPriority().getId());
			task.setPerson(p);
			task.setStatus(s);
			task.setPriority(r);
			Task t=task_repository.save(task);
			p.getTasks().add(t);
			s.getTasks().add(t);
			r.getTasks().add(t);
			person_repository.save(p);
			status_repository.save(s);
			priority_repository.save(r);
			return t;
		}catch(NullPointerException ex) {
			throw new Save_task_exception("Can't save or update a task without person, priority and status");
		}
	}

	public List<Task> get_ordered_tasks() {
		List<Task> tasks=new ArrayList<>();
		task_repository.findAll().forEach(tasks::add);
		return tasks.stream()
				.filter(t->t.getStatus().getId()==1)
				.sorted((t1,t2)->t2.getPriority().getValue()-t1.getPriority().getValue())
				.collect(Collectors.toList());
	}
}
