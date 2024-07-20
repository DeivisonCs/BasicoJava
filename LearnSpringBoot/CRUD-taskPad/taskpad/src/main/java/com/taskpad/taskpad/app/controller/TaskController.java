package com.taskpad.taskpad.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Task getTask(@RequestParam @PathVariable("id") Integer id){
        return service.getTaskById(id);
    }

    @GetMapping
    public List<Task> getAll(){
        return service.getAllTasks();
    }

    @PostMapping
    public void addTask(@Valid @RequestBody TaskDTO newTask){
        service.addTask(newTask);
    }

    // @PutMapping
    // public void updateTask(){

    // }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") Integer id){
        service.deleteTask(id);
    }
}
