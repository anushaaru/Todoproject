package com.todoapp.todoproject.controller;

import com.todoapp.todoproject.entity.Task;
import com.todoapp.todoproject.exceptions.TaskHandleException;
import com.todoapp.todoproject.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TaskController {

    public static final Logger logger =  LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @GetMapping("/tasklist/{userId}")
    public ResponseEntity<List<Task>> getListOfTasksByUserId(@PathVariable("userId") String userId) {
        logger.info("Get List of tasks by user id:"+userId);
        try {
            return taskService.getListOfTasksByUserId(userId);

        } catch (Exception ex) {
            throw new TaskHandleException(ex);
        }
    }

    //returns all tasks
    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getListOfTasks() {
        try {
            return taskService.getAllTasks();
        }
        catch (Exception ex) {
            throw new TaskHandleException(ex);
        }
    }

    //returns a task
    @GetMapping("/task/{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") int id) {
        logger.info("Get Task by id: " + id);
        try {

            return taskService.getTaskById(id);
        }
        catch (Exception ex) {
            throw new TaskHandleException(ex);
        }
    }

    //adds a task
    @PostMapping("/tasks/task")
    public ResponseEntity<String> createTask(@RequestBody Task task) {
        logger.info("User adding new task");
        try {
            return taskService.addOrUpdateTask(task);
        }
        catch (Exception ex) {
            throw new TaskHandleException(ex);
        }

    }

    //deletes a task
    @DeleteMapping("/deletetask/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") int id) {
        logger.info("Deleting a task");
        try {
            return taskService.deleteTask(id);
        }
        catch (Exception ex) {
            throw new TaskHandleException(ex);
        }
    }



}
