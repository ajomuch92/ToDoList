package com.agile.toDoList.ToDoList;

import java.util.Arrays;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.agile.toDoList.ToDoList.Classes.Person;
import com.agile.toDoList.ToDoList.Classes.Priority;
import com.agile.toDoList.ToDoList.Classes.Status;
import com.agile.toDoList.ToDoList.Classes.Task;
import com.agile.toDoList.ToDoList.Repositories.Person_repository;
import com.agile.toDoList.ToDoList.Repositories.Priority_repository;
import com.agile.toDoList.ToDoList.Repositories.Status_repository;
import com.agile.toDoList.ToDoList.Repositories.Task_repository;

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
	return (evt) -> Arrays.asList("Pending,In process,Done".split(","))
	.forEach(a -> {
		status_repository.save(new Status(a));
	});
	}
	
	@Bean
	CommandLineRunner start(Person_repository person_repository) {
	return (evt) -> Arrays.asList("Aarón,Carlos,Alexandra,Fernando,Gerardo,Audeli".split(","))
	.forEach(a -> {
		person_repository.save(new Person(a));
	});
	}
	
	@Bean
	CommandLineRunner startTask(Task_repository task_repository) {
	return (evt) -> Arrays.asList("Task 1,Task 2,Task 4,Task 4,Task 5,Task x".split(","))
	.forEach(a -> {
		Task t=new Task();
		t.setDate(new Date());
		t.setName(a);
		Person p=new Person();
		p.setId((int) (Math.random() * 6)+1);
		t.setPerson(p);
		Status s=new Status();
		s.setId((int) (Math.random() * 3)+1);
		t.setStatus(s);
		Priority pr=new Priority();
		pr.setId((int) (Math.random() * 3)+1);
		t.setPriority(pr);
		task_repository.save(t);
	});
	}
}
