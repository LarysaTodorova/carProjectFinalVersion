package com.example.cardataproject.controller;

import com.example.cardataproject.dto.producerDTO.ProducerResponse;
import com.example.cardataproject.service.producerService.AddProducerService;
import com.example.cardataproject.service.producerService.DeleteProducerService;
import com.example.cardataproject.service.producerService.FindProducerService;
import com.example.cardataproject.service.producerService.UpdateProducerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProducerController.class)
class ProducerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddProducerService addProducerService;

    @MockBean
    private DeleteProducerService deleteProducerService;

    @MockBean
    private FindProducerService findProducerService;

    @MockBean
    private UpdateProducerService updateProducerService;

    @Test
    void createNewProducer() throws Exception {

        ProducerResponse response = new ProducerResponse(1, "BMW", "123456", "bmw@mail.com");

        when(addProducerService.createProducer(any())).thenReturn(response);

        mockMvc.perform(post("/api/producers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "BMW",
                                  "phoneNumber": "123456",
                                  "email": "bmw@mail.com",
                                  "password": "99999"                                                                      
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.producerId").value(1))
                .andExpect(jsonPath("$.name").value("BMW"))
                .andExpect(jsonPath("$.phoneNumber").value("123456"))
                .andExpect(jsonPath("$.email").value("bmw@mail.com"));

    }

    @Test
    void findByName() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void updateProducer() {
    }

    @Test
    void deleteProducerById() {
    }
}