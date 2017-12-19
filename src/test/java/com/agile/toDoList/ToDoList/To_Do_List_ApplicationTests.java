package com.agile.toDoList.ToDoList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.agile.toDoList.ToDoList.Classes.Task;
import com.agile.toDoList.ToDoList.Services.Person_service;
import com.agile.toDoList.ToDoList.Services.Priority_service;
import com.agile.toDoList.ToDoList.Services.Status_service;
import com.agile.toDoList.ToDoList.Services.Task_service;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = To_Do_List_Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class To_Do_List_ApplicationTests {
    private Task task;
    private Set<Task> tasks=new HashSet<>();
    
    @Autowired
    Person_service person_service;
    @Autowired
    Priority_service priority_service;
    @Autowired
    Status_service status_service;
    @Autowired
    Task_service task_service;
    
    
    @Before
    public void setup() throws Exception {
    	task=new Task();
    	task.setName("Task 1");
    	task.setDate(new Date());
    	task.setPerson(person_service.get_person(4));
    	task.setPriority(priority_service.get_priority(3));
    	task.setStatus(status_service.get_one_status(2));
    }
    
    @Test
    public void method_a() throws Exception {
    	Task t=task_service.add_task(task);
    	task.setId(t.getId());
    	assertEquals( task.getName(), t.getName());
    }
    
    @Test
    public void method_b() throws Exception {;
    	assertEquals(this.task.getPerson().getName(), task_service.get_task(7).getPerson().getName());
    }
    
    @Test
    public void method_c() throws Exception {
    	Task t=new Task();
    	t.setDate(new Date());
    	t.setName("Meeting");
    	t.setStatus(status_service.get_one_status(3));
    	t.setPerson(person_service.get_person(2));
    	t.setPriority(priority_service.get_priority(3));
    	assertEquals(t.getStatus().getId(), task_service.update_task(7, t).getStatus().getId());
    }
    
    @Test
    public void method_d() throws Exception {
    	task_service.get_tasks().forEach(tasks::add);
    	assertEquals(tasks, task_service.get_tasks());
    }
    
    @Test
    public void method_e() throws Exception {
    	assertNotEquals(this.task, task_service.get_task(2));
    }
    
    @Test
    public void method_f() throws Exception {
    	assertTrue(task_service.delete_task(7));
    }
 }
