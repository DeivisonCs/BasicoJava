package com.taskpad.taskpad.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taskpad.taskpad.app.dto.OwnerDTO;
import com.taskpad.taskpad.app.exceptions.NotFoundException;
import com.taskpad.taskpad.app.mapper.OwnerMapper;
import com.taskpad.taskpad.app.models.Owner;
import com.taskpad.taskpad.app.repository.OwnerRep;

@Service
public class OwnerService {
    @Autowired
    private OwnerRep ownerDB;

    @Transactional(readOnly = true)
    public Owner getOwnerById(Integer id){   
        return ownerDB.findById(id).orElseThrow(NotFoundException::new);
    }
    
    @Transactional(readOnly = true)
    public List<Owner> getAllOwners(){
        return ownerDB.findAll();
    }
    
    @Transactional
    public Owner addOwner(OwnerDTO newOwner){
        return ownerDB.save(OwnerMapper.OwnerDTOtoEntity(newOwner));
    }
    
    @Transactional
    public void deleteOwner(Integer id){
        if(!ownerDB.existsById(id)) throw new NotFoundException("Owner not Found");

        ownerDB.deleteById(id);
    }

    // @Transactional
    // public void updateOwner(OwnerDTO newOwner){
    //     ownerDB.save(newOwner);
    // }
}
