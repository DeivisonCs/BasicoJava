package com.taskpad.taskpad.app.dto;

import java.sql.Date;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TaskDTO {
    @NotNull
    private Integer owner_id;

    @NotEmpty
    @Size(max = 30)
    private String title;
    
    @NotEmpty
    @Size(max = 300)
    private String description;

    @NotNull
    private boolean completed;

    @NotNull
    @FutureOrPresent
    private Date deadline;
}
