package com.agile.toDoList.ToDoList.Classes;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Task {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private Date date;
	
	@ManyToOne
	//@JsonIgnore
	//@JoinColumn(name="id")
	private Person person;
	
	@ManyToOne
	//@JsonIgnore
	//@JoinColumn(name="id")
	private Status status;
	
	@ManyToOne
	//@JsonIgnore
	//@JoinColumn(name="id")
	private Priority priority;

	public Task() {}

	public Task(String name, Date date) {
		this.name = name;
		this.date = date;
	}

	public Task(int id, String name, Date date) {
		this.id = id;
		this.name = name;
		this.date = date;
	}

	public Task(int id, String name, Date date, Person person, Status status, Priority priority) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.person = person;
		this.status = status;
		this.priority = priority;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String task_Name) {
		this.name = task_Name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "Task =" + name + "(" + date + ", " + person + ", " + status
				+ ", " + priority + ")";
	}
	
	
}
