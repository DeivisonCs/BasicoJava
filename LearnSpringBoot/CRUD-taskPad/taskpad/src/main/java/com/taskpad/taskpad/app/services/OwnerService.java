package com.taskpad.taskpad.app.services;

import java.util.List;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taskpad.taskpad.app.dto.owner.OwnerAddDTO;
import com.taskpad.taskpad.app.dto.owner.OwnerUpdateDTO;
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
    public Owner addOwner(OwnerAddDTO newOwner){
        return ownerDB.save(OwnerMapper.OwnerDTOtoEntity(newOwner));
    }
    
    @Transactional
    public void deleteOwner(Integer id){
        if(!ownerDB.existsById(id)) throw new NotFoundException("Owner not Found");

        ownerDB.deleteById(id);
    }

    @Transactional
    public Owner updateOwner(OwnerUpdateDTO newOwnerDTO, Integer id){
        Owner ownerToUpdate = ownerDB.findById(id).orElseThrow(() -> new NotFoundException("Owner Not Found!"));

        // System.out.println("before: " +ownerToUpdate);
        // ownerToUpdate = OwnerMapper.OwnerDTOtoEntity(newOwnerDTO);
        // System.out.println("after: " +ownerToUpdate);
        // BeanUtils?

        // System.out.println("before: " +ownerToUpdate);
        copyNonNullProperties(newOwnerDTO, ownerToUpdate);
        // System.out.println("after: " +ownerToUpdate);

        return ownerDB.save(ownerToUpdate);
    }

    private void copyNonNullProperties(Object source, Object target) {
        BeanWrapper src = new BeanWrapperImpl(source);
        BeanWrapper trg = new BeanWrapperImpl(target);

        for (java.beans.PropertyDescriptor pd : src.getPropertyDescriptors()) {
            String propertyName = pd.getName();
            // Ignore the "class" property
            if (!"class".equals(propertyName)) {
                Object srcValue = src.getPropertyValue(propertyName);
                if (srcValue != null) {
                    trg.setPropertyValue(propertyName, srcValue);
                }
            }
        }
    }
}
