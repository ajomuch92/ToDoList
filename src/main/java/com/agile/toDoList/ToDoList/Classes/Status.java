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
public class Status {
	@Id
	@GeneratedValue
	private int id;
	private String status;
	@OneToMany(mappedBy="status", fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<Task> tasks=new HashSet<>();
	
	public Status() {}

	public Status(String status_name) {
		this.status = status_name;
	}

	public Status(int id, String status_name) {
		this.id = id;
		this.status = status_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus_name() {
		return status;
	}

	public void setStatus_name(String status_name) {
		this.status = status_name;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public String toString() {
		return  status ;
	}
	
	
}
