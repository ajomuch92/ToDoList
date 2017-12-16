package com.agile.toDoList.ToDoList.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.agile.toDoList.ToDoList.Classes.Priority;

@Repository
public interface Priority_repository extends CrudRepository<Priority, Integer> {
	Optional<Priority> findById(int id);
	Optional<Priority> findByName(String name);
	//Optional<Priority> findbByName(String name);
}
