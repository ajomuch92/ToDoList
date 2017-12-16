package com.agile.toDoList.ToDoList.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.agile.toDoList.ToDoList.Classes.Status;

@Repository
public interface Status_repository extends CrudRepository<Status, Integer> {
	Optional<Status> findById(int id);
	Optional<Status> findByStatus(String status);
}
