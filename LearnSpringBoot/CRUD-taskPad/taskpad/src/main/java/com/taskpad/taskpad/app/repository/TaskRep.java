package com.taskpad.taskpad.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskpad.taskpad.app.models.Task;

public interface TaskRep extends JpaRepository<Task, Integer>{
    
}
