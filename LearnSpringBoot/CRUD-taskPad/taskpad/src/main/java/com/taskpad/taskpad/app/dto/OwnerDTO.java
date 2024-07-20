package com.taskpad.taskpad.app.dto;

import java.sql.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDTO {
    @NotEmpty
    @Size(max = 30)
    private String name;
    
    @NotEmpty
    @Email
    @Size(max = 30)
    private String email;
    
    @NotNull
    @Past
    private Date birthDate;
}
