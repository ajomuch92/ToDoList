package com.agile.toDoList.ToDoList.Classes;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Priority {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private int value;
	@OneToMany(mappedBy="priority", fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<Task> tasks=new HashSet<>();
	
	public Priority() {}

	public Priority(String name_priority, int value) {
		this.name = name_priority;
		this.value = value;
	}

	public Priority(int id, String name, int value) {
		this.id = id;
		this.name = name;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName_priority() {
		return name;
	}

	public void setName_priority(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public String toString() {
		return name + "(" + value + ")";
	}
	
	
}
