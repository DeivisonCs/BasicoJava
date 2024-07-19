package com.taskpad.taskpad.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskpad.taskpad.app.models.Owner;
import com.taskpad.taskpad.app.repository.OwnerRep;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    @Autowired
    private OwnerRep ownerDB;

    @GetMapping
    public List<Owner> getAll(){
        return ownerDB.findAll(); 
    }

    @GetMapping("/{id}")
    public Owner getOwner(@PathVariable("id") Integer id){
        return ownerDB.getReferenceById(id);
    }

    @PostMapping
    public Owner addOwner(Owner newOwner){
        return ownerDB.save(newOwner);
    }

    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable("id") Integer id){
        ownerDB.deleteById(id);
    }
}
