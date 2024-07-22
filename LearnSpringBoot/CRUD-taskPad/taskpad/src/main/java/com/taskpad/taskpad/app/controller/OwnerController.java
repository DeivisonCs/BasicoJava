package com.taskpad.taskpad.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskpad.taskpad.app.dto.owner.OwnerAddDTO;
import com.taskpad.taskpad.app.dto.owner.OwnerUpdateDTO;
import com.taskpad.taskpad.app.exceptions.MissingArgsException;
import com.taskpad.taskpad.app.models.Owner;
import com.taskpad.taskpad.app.services.OwnerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    @Autowired
    private OwnerService service;

    @GetMapping
    public ResponseEntity<List<Owner>> getAll(){
        return ResponseEntity.ok(service.getAllOwners());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwner(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.getOwnerById(id));
    }

    @PostMapping
    public ResponseEntity<Owner> addOwner(@Valid @RequestBody OwnerAddDTO newOwner, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MissingArgsException();
        }

        return ResponseEntity.ok(service.addOwner(newOwner));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOwner(@PathVariable("id") Integer id){
        service.deleteOwner(id);
        return ResponseEntity.ok("User Deleted!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Owner> updateOwner (@PathVariable("id") Integer id, @Valid @RequestBody OwnerUpdateDTO newDatas, BindingResult bindingResult){
        if(bindingResult.hasErrors()) 
            throw new MissingArgsException();

        return ResponseEntity.ok(service.updateOwner(newDatas, id));
    }
}
