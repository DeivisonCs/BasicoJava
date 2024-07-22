package com.taskpad.taskpad.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskpad.taskpad.app.dto.task.TaskAddDTO;
import com.taskpad.taskpad.app.dto.task.TaskUpdateDTO;
import com.taskpad.taskpad.app.exceptions.MissingArgsException;
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
    public ResponseEntity<Task> addTask(@Valid @RequestBody TaskAddDTO newTask, BindingResult bindingResult){
        if(bindingResult.hasErrors()) throw new MissingArgsException();

        return ResponseEntity.ok(service.addTask(newTask));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Integer id, @RequestBody TaskUpdateDTO newDatas, BindingResult bindingResult){
        return ResponseEntity.ok(service.updateTask(newDatas, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Integer id){
        service.deleteTask(id);
        return ResponseEntity.ok("Task Deleted!");
    }
}
