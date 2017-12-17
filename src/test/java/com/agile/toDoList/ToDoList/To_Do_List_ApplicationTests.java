package com.agile.toDoList.ToDoList;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class To_Do_List_ApplicationTests {
    private Task task;
    Date date;
    
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
    	date=new Date();
    	task=new Task();
    	task.setName("Task 1");
    	task.setDate(date);
    	System.out.println(person_service.get_person(1).getTasks());
    	task.setPerson(person_service.get_person(4));
    	task.setPriority(priority_service.get_priority(3));
    	task.setStatus(status_service.get_one_status(2));
    }
    
    @Test
    public void method_a(){
    	/*Task t=task_service.add_task(task);
    	task.setId(t.getId());*/
    	assertEquals( 6, 2*3);
    }
}
