package com.agile.toDoList.ToDoList.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.agile.toDoList.ToDoList.Classes.Person;

@Repository
public interface Person_repository extends CrudRepository<Person, Integer> {
	Optional<Person> findById(int id);
	Optional<Person> findByName(String name);
}
