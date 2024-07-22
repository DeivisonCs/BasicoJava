package com.taskpad.taskpad.app.mapper;

import com.taskpad.taskpad.app.dto.task.TaskAddDTO;
import com.taskpad.taskpad.app.models.Task;

public class TaskMapper {
    public static TaskAddDTO TaskToDTO (Task task){
        if(task == null) return null;

        TaskAddDTO taskDTO = new TaskAddDTO(
            task.getOwner().getId(), 
            task.getTitle(),
            task.getDescription(), 
            task.isCompleted(), 
            task.getDeadline()
        );

        return taskDTO;
    }    

    public static Task TaskDTOtoEntity(TaskAddDTO taskDTO){
        if(taskDTO == null) return null;

        return Task.builder()
                .title(taskDTO.getTitle())
                .description(taskDTO.getDescription())
                .deadline(taskDTO.getDeadline())
                .completed(taskDTO.getCompleted())
                .build();
    }
}
