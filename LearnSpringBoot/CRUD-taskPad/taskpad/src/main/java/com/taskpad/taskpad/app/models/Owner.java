package com.taskpad.taskpad.app.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cod_owner")
    private Integer id; 

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String birthDate;

    @OneToMany(mappedBy = "owner")
    private List<Task> tasks;

    @Override
    public String toString() {
        return "Name: " + name +
                "Birth Date: " + birthDate;
    }

// ------------- Getters and Setters -------------

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    
}
