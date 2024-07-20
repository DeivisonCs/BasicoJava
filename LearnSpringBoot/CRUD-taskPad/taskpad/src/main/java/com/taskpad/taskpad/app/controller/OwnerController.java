package com.taskpad.taskpad.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskpad.taskpad.app.dto.OwnerDTO;
import com.taskpad.taskpad.app.models.Owner;
import com.taskpad.taskpad.app.services.OwnerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    @Autowired
    private OwnerService service;

    @GetMapping
    public List<Owner> getAll(){
        // Owner owner = 
        return service.getAllOwners();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwner(@PathVariable("id") Integer id){
        Owner owner = service.getOwnerById(id);
        return ResponseEntity.ok(owner);
    }

    @PostMapping
    public void addOwner(@Valid @RequestBody OwnerDTO newOwner){
        service.addOwner(newOwner);
    }

    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable("id") Integer id){
        service.deleteOwner(id);
    }
}
