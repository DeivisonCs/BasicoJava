package com.taskpad.taskpad.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class OwnerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private static final String BASE_URL = "/owners";
    private static final String CONTENT_TYPE = MediaType.APPLICATION_JSON_VALUE;

    private String createOwnerJSON(String name, String email, String birthDate){
        return String.format("{\"name\": \"%s\", \"email\": \"%s\", \"birthDate\": \"%s\"}", name, email, birthDate);
    }

    @Test
    public void testCreateOwner() throws Exception{
        String ownerJson = createOwnerJSON("name test", "testeEmail@gmail.com", "2020-02-20");

        mockMvc.perform(
            post(BASE_URL)
            .contentType(CONTENT_TYPE)
            .content(ownerJson))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(CONTENT_TYPE))
        .andExpect(jsonPath("$.id").isNumber())
        .andExpect(jsonPath("$.name").value("name test"))
        .andExpect(jsonPath("$.email").value("testeEmail@gmail.com"))
        .andExpect(jsonPath("$.birthDate").value("2020-02-20")); 
    }

    @Test
    public void testGetOwnerById() throws Exception{
        mockMvc.perform(
            get(BASE_URL + "/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(CONTENT_TYPE))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("name test"))
            .andExpect(jsonPath("$.email").value("testeEmail@gmail.com"))
            .andExpect(jsonPath("$.birthDate").value("2020-02-20"));
    }

    @Test
    public void testDeleteOwner() throws Exception{
        // String onwerJson = createOwnerJSON("test delete", "deletetest@gmail.com", "1990-09-12");

        // mockMvc.perform(
        //     post(BASE_URL)
        //     .contentType(CONTENT_TYPE)
        //     .content(onwerJson))
        //     .andExpect(status().isCreated());

        mockMvc.perform(
            delete(BASE_URL + "/1"))
            .andExpect(status().isNoContent());

        mockMvc.perform(
            get(BASE_URL +"/1"))
            .andExpect(status().isNotFound());

    }

    
    // @Test
    // public void testGetAllOwners() throws Exception {


    //     mockMvc.perform(get(BASE_URL))

    // }
}