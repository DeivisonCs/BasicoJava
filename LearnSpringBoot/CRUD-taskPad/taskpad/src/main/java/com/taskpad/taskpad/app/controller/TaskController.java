package com.taskpad.taskpad.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskpad.taskpad.app.models.Task;
import com.taskpad.taskpad.app.repository.TaskRep;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskRep taskDB;

    @GetMapping("{id}")
    public Task getTask(@PathVariable("id") Integer id){
        return taskDB.getReferenceById(id);
    }

    @GetMapping
    public List<Task> getAll(){
        return taskDB.findAll();
    }

    @PostMapping
    public Task addTask(@Valid @RequestBody Task newTask){
        return taskDB.save(newTask);
    }

    // @PutMapping
    // public void updateTask(){

    // }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") Integer id){
        taskDB.deleteById(id);
    }
}
