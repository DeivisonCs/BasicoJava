package com.taskpad.taskpad.app.mapper;

import com.taskpad.taskpad.app.dto.owner.OwnerAddDTO;
import com.taskpad.taskpad.app.models.Owner;


public class OwnerMapper {
    public static OwnerAddDTO OwnerToDTO(Owner owner){

        if(owner == null) return null;
        

        OwnerAddDTO ownerDTO = new OwnerAddDTO(
            owner.getName(), 
            owner.getEmail(), 
            owner.getBirthDate()
        );

        return ownerDTO;
    }

    public static Owner OwnerDTOtoEntity(OwnerAddDTO ownerDTO){

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
