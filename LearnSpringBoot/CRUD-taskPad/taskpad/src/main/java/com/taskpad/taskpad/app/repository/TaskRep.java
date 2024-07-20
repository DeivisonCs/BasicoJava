package com.taskpad.taskpad.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskpad.taskpad.app.dto.TaskDTO;

public interface TaskRep extends JpaRepository<TaskDTO, Integer>{
    
}
