package com.taskpad.taskpad.app.mapper;

import com.taskpad.taskpad.app.dto.OwnerDTO;
import com.taskpad.taskpad.app.models.Owner;

public class OwnerMapper {
    public static OwnerDTO OwnerToDTO(Owner owner){

        if(owner == null) return null;
        

        OwnerDTO ownerDTO = new OwnerDTO(
            owner.getName(), 
            owner.getEmail(), 
            owner.getBirthDate()
        );

        return ownerDTO;
    }

    public static Owner toEntity(OwnerDTO ownerDTO){

        if(ownerDTO == null) return null;
        
        Owner owner = new Owner();
        owner.setName(ownerDTO.getName());
        owner.setEmail(ownerDTO.getEmail());
        owner.setBirthDate(ownerDTO.getBirthDate());

        return owner;
    }
}
