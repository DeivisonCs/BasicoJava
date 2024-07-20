package com.taskpad.taskpad.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskpad.taskpad.app.dto.OwnerDTO;

public interface OwnerRep extends JpaRepository<OwnerDTO, Integer>{
    
}
