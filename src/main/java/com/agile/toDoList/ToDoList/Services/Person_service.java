package com.agile.toDoList.ToDoList.Services;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agile.toDoList.ToDoList.Classes.Person;
import com.agile.toDoList.ToDoList.Classes.Task;
import com.agile.toDoList.ToDoList.Exceptions.Delete_not_allowed_exception;
import com.agile.toDoList.ToDoList.Exceptions.Person_not_found_exception;
import com.agile.toDoList.ToDoList.Repositories.Person_repository;

@Service
public class Person_service {
	
	@Autowired
	Person_repository person_repository;
	private static final Logger logger=Logger.getLogger(Person_service.class);
	
	public Iterable<Person> get_persons() {
		logger.info("Getting all persons");
		return person_repository.findAll();
	}

	public Person add_person(Person person) {
		logger.info("Adding a person named "+person.getName());
		return person_repository.save(person);
	}

	public Person get_person(int id) {
		if(!person_repository.findById(id).isPresent()) {
			logger.error("Person with id="+id+" not found");
			throw new Person_not_found_exception(id);
		}
		logger.info("Getting a person with id="+id);
		return person_repository.findOne(id);
	}

	public Person update_person(int id, Person person) {
		if(!person_repository.findById(id).isPresent()) {
			logger.error("Person with id="+id+" not found");
			throw new Person_not_found_exception(id);
		}
		person.setId(id);
		person.setTasks(person_repository.findOne(id).getTasks());
		logger.info("Update people with id="+id);
		return person_repository.save(person);
	}

	public boolean delete_person(int id) {
		if(!person_repository.findById(id).isPresent()) {
			logger.error("Person with id="+id+" not found");
			throw new Person_not_found_exception(id);
		}
		if(!person_repository.findOne(id).getTasks().isEmpty()) {
			logger.error("Can't delete the person("+id+") because it has dependecies");
			throw new Delete_not_allowed_exception("You can't delete the person with id="+id+"(Integrity referential)");
		}
		logger.warn("Delete person with id="+id);
		person_repository.delete(id);
		return true;
	}

	public Set<Task> get_person_tasks(int id) {
		if(!person_repository.findById(id).isPresent()) {
			logger.error("Person with id="+id+" not found");
			throw new Person_not_found_exception(id);
		}
		logger.info("Finding person's tasks");
		return person_repository.findOne(id).getTasks();
	}
	

}
