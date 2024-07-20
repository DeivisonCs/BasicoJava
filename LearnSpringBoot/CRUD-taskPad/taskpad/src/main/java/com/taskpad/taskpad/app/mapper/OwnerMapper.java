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

    public static Owner OwnerDTOtoEntity(OwnerDTO ownerDTO){

        if(ownerDTO == null) return null;

        return Owner.builder()
                    .name(ownerDTO.getName())
                    .email(ownerDTO.getEmail())
                    .birthDate(ownerDTO.getBirthDate())
                    .build();

        // Owner owner = new Owner();
        // owner.setName(ownerDTO.getName());
        // owner.setEmail(ownerDTO.getEmail());
        // owner.setBirthDate(ownerDTO.getBirthDate());

        // return owner;
    }
}
