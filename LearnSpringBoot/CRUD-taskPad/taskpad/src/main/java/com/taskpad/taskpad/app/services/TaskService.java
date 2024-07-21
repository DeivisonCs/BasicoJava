package com.taskpad.taskpad.app.services;
import java.util.List;

import com.taskpad.taskpad.app.exceptions.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taskpad.taskpad.app.dto.TaskDTO;
import com.taskpad.taskpad.app.mapper.TaskMapper;
import com.taskpad.taskpad.app.models.Task;
import com.taskpad.taskpad.app.repository.TaskRep;

@Service
public class TaskService {
    @Autowired
    private TaskRep taskDB;

    @Transactional(readOnly = true)
    public Task getTaskById(Integer id){
        return taskDB.findById(id).orElseThrow(NotFoundException::new); 
    }
    
    @Transactional(readOnly = true)
    public List<Task> getAllTasks(){
        return taskDB.findAll();
    }

    @Transactional
    public Task addTask(TaskDTO newTask){
        return taskDB.save(TaskMapper.TaskDTOtoEntity(newTask));
    }
    
    @Transactional
    public void deleteTask(Integer id){
        if(!taskDB.existsById(id)) throw new NotFoundException("Task Not Found!");

        taskDB.deleteById(id);
    }
}
