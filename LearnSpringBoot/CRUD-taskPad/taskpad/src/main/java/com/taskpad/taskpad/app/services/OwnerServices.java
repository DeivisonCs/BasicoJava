package com.taskpad.taskpad.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taskpad.taskpad.app.dto.OwnerDTO;
import com.taskpad.taskpad.app.repository.OwnerRep;

@Service
public class OwnerServices {
    @Autowired
    private OwnerRep ownerDB;

    @Transactional(readOnly = true)
    public OwnerDTO getOwnerById(Integer id){
        
        return ownerDB.getReferenceById(id);
    }
    
    @Transactional(readOnly = true)
    public List<OwnerDTO> getAllOwners(){
        return ownerDB.findAll();
    }
    
    @Transactional
    public void addOwner(OwnerDTO newOwner){
        ownerDB.save(newOwner);
    }
    
    // @Transactional
    // public void updateOwner(OwnerDTO newOwner){
    //     ownerDB.save(newOwner);
    // }
}
