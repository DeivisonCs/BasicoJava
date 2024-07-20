package com.taskpad.taskpad.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskpad.taskpad.app.models.Owner;
import com.taskpad.taskpad.app.repository.OwnerRep;
import com.taskpad.taskpad.app.services.OwnerServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    @Autowired
    private OwnerServices service;

    @GetMapping
    public List<Owner> getAll(){
        return service.getAllOwners();
    }

    @GetMapping("/{id}")
    public Owner getOwner(@PathVariable("id") Integer id){
        
    }

    @PostMapping
    public Owner addOwner(@Valid @RequestBody Owner newOwner){
        
    }

    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable("id") Integer id){
        
    }
}
