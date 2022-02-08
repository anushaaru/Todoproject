package com.todoapp.todoproject.service;

import com.todoapp.todoproject.entity.Task;
import com.todoapp.todoproject.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void testaddOrUpdate(){

        Task task = new Task(1,new Date(2022-01-02), "new todo", "new", "1", new Date(2022-02-06));
        Mockito.when(taskRepository.save(task)).thenReturn(task);
        ResponseEntity<String> actual = taskService.addOrUpdateTask(task);
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals("Task Added Successfully", actual.getBody());

    }

    @Test
    public void testGetAllTasks() throws Exception {
        List<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task(1,new Date(2022-01-02), "new todo", "new", "1", new Date(2022-02-06)));
        tasks.add(new Task(2,new Date(2022-01-03), "todo", "updated", "1", new Date(2022-02-05)));
        Mockito.when(taskRepository.findAll()).thenReturn(tasks);

        ResponseEntity<List<Task>> actualTasks = taskService.getAllTasks();

        assertEquals(HttpStatus.OK, actualTasks.getStatusCode());

    }

    @Test
    public void testGetAlltasks_with_no_tasks() throws Exception{
        List<Task> tasks = new ArrayList<Task>();
        Mockito.when(taskRepository.findAll()).thenReturn(tasks);
        ResponseEntity<List<Task>> actualTasks = taskService.getAllTasks();
        //assertThrows(TaskNotFoundException.class,()->taskService.getAllTasks());
        assertEquals(HttpStatus.NO_CONTENT, actualTasks.getStatusCode());

    }

    @Test
    public void testGetById(){
        Optional<Task> user = Optional.empty();
        Task task = new Task(1,new Date(2022-01-02), "new todo", "new", "1", new Date(2022-02-06));
        Optional<Task> userOptional = Optional.of(task);
        Mockito.when(taskRepository.findById(task.getId())).thenReturn(userOptional);
        ResponseEntity<Task> actual = taskService.getTaskById(task.getId());

        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    public void testDeleteTask(){
        Optional<Task> user = Optional.empty();
        Task task = new Task(1,new Date(2022-01-03), "new todo", "new", "1", new Date(2022-02-07));
        Optional<Task> userOptional = Optional.of(task);
        Mockito.when(taskRepository.findById(task.getId())).thenReturn(userOptional);

        ResponseEntity<String> actual = taskService.deleteTask(task.getId());
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals("Task Deleted Successfully", actual.getBody());
    }



}
