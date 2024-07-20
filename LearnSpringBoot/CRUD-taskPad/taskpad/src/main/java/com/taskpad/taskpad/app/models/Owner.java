package com.taskpad.taskpad.app.models;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

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

@Entity(name = "owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cod_owner")
    private Integer id; 

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Date birthDate;

    @OneToMany(mappedBy = "owner")
    private List<Task> tasks;

}
