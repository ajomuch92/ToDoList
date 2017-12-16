package com.agile.toDoList.ToDoList.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.agile.toDoList.ToDoList.Classes.Task;

@Repository
public interface Task_repository extends CrudRepository<Task,Integer> {
	Optional<Task> findById(int id);
}
