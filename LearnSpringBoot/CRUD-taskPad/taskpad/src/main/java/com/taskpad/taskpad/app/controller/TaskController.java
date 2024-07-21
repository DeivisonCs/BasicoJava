package com.taskpad.taskpad.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskpad.taskpad.app.dto.TaskDTO;
import com.taskpad.taskpad.app.models.Task;
import com.taskpad.taskpad.app.services.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService service;

    @GetMapping("{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.getTaskById(id));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAll(){
        return ResponseEntity.ok(service.getAllTasks());
    }

    @PostMapping
    public ResponseEntity<Task> addTask(@Valid @RequestBody TaskDTO newTask){
        return ResponseEntity.ok(service.addTask(newTask));
    }

    // @PutMapping
    // public void updateTask(){

    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Integer id){
        service.deleteTask(id);
        return ResponseEntity.ok("Task Deleted!");
    }
}
