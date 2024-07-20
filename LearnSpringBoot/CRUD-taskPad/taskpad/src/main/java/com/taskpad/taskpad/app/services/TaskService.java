package com.taskpad.taskpad.app.services;

import java.util.List;

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
        return taskDB.getReferenceById(id); 
    }
    
    @Transactional(readOnly = true)
    public List<Task> getAllTasks(){
        return taskDB.findAll();
    }

    @Transactional
    public void addTask(TaskDTO newTask){
        taskDB.save(TaskMapper.TaskDTOtoEntity(newTask));
    }
    
    @Transactional
    public void deleteTask(Integer id){
        taskDB.deleteById(id);
    }
}
