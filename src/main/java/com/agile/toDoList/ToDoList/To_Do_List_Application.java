package com.agile.toDoList.ToDoList;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.agile.toDoList.ToDoList.Classes.Person;
import com.agile.toDoList.ToDoList.Classes.Priority;
import com.agile.toDoList.ToDoList.Classes.Status;
import com.agile.toDoList.ToDoList.Repositories.Person_repository;
import com.agile.toDoList.ToDoList.Repositories.Priority_repository;
import com.agile.toDoList.ToDoList.Repositories.Status_repository;

@SpringBootApplication
public class To_Do_List_Application {

	public static void main(String[] args) {
		SpringApplication.run(To_Do_List_Application.class, args);
	}
	
	@Bean
	CommandLineRunner begin(Priority_repository priority_repository) {
	return (evt) -> Arrays.asList("High 10,Medium 5,Low 0".split(","))
	.forEach(a -> {
		String[] s=a.split(" ");
		priority_repository.save(new Priority(s[0],Integer.valueOf(s[1])));
	});
	}
	
	@Bean
	CommandLineRunner init(Status_repository status_repository) {
	return (evt) -> Arrays.asList("Pending, In process, Done".split(","))
	.forEach(a -> {
		status_repository.save(new Status(a));
	});
	}
	
	@Bean
	CommandLineRunner start(Person_repository person_repository) {
	return (evt) -> Arrays.asList("Aarón, Carlos, Alexandra, Fernando, Gerardo".split(","))
	.forEach(a -> {
		person_repository.save(new Person(a));
	});
	}
}
