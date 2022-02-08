package com.todoapp.todoproject.service;

import com.todoapp.todoproject.entity.Task;
import com.todoapp.todoproject.exceptions.TaskHandleException;
import com.todoapp.todoproject.exceptions.TaskNotFoundException;
import com.todoapp.todoproject.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public ResponseEntity<String> addOrUpdateTask(Task task){
        taskRepository.save(task);
        return new ResponseEntity<String>("Task Added Successfully", HttpStatus.OK);
    }


    public ResponseEntity<List<Task>> getAllTasks() {

          List<Task> tasks = taskRepository.findAll();
          if(tasks.isEmpty()){
              return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);
          }
          return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);

    }

    public ResponseEntity<List<Task>> getListOfTasksByUserId(String userId){
        List<Task> tasks = taskRepository.findByUserId(userId);

        if (tasks.isEmpty()) {
            return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
    }

    public ResponseEntity<Task> getTaskById(int id){

       Task task = taskRepository.findById(id).get();
        if(task == null){
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteTask(int id){
        taskRepository.deleteById(id);
        return new ResponseEntity<String>("Task Deleted Successfully", HttpStatus.OK);
    }

}
