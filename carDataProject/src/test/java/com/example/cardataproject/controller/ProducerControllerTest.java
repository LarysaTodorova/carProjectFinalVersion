package com.example.cardataproject.controller;

import com.example.cardataproject.service.producerService.AddProducerService;
import com.example.cardataproject.service.producerService.DeleteProducerService;
import com.example.cardataproject.service.producerService.FindProducerService;
import com.example.cardataproject.service.producerService.UpdateProducerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;



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
    void createNewProducer() {
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