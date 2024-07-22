package com.taskpad.taskpad.app.services;
import java.util.List;

import com.taskpad.taskpad.app.dto.task.TaskAddDTO;
import com.taskpad.taskpad.app.dto.task.TaskUpdateDTO;
import com.taskpad.taskpad.app.exceptions.NotFoundException;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Task addTask(TaskAddDTO newTask){
        return taskDB.save(TaskMapper.TaskDTOtoEntity(newTask));
    }
    
    @Transactional
    public void deleteTask(Integer id){
        if(!taskDB.existsById(id)) throw new NotFoundException("Task Not Found!");

        taskDB.deleteById(id);
    }

    @Transactional
    public Task updateTask(TaskUpdateDTO newTaskDTO, Integer id){
        Task taskToUpdate = taskDB.findById(id).orElseThrow(() -> new NotFoundException("Task Not Found!"));

        copyNonNullProperties(newTaskDTO, taskToUpdate);

        return taskDB.save(taskToUpdate);
    }

    private void copyNonNullProperties(Object source, Object target) {
        BeanWrapper src = new BeanWrapperImpl(source);
        BeanWrapper trg = new BeanWrapperImpl(target);

        for (java.beans.PropertyDescriptor pd : src.getPropertyDescriptors()) {
            String propertyName = pd.getName();
            // Ignore the "class" property
            if (!"class".equals(propertyName)) {
                Object srcValue = src.getPropertyValue(propertyName);
                if (srcValue != null) {
                    trg.setPropertyValue(propertyName, srcValue);
                }
            }
        }
    }
}
