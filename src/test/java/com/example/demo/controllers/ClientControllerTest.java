package com.example.demo.controllers;

import com.example.demo.entity.Client;
import com.example.demo.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.SpringBootDependencyInjectionTestExecutionListener;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.mockito.Mockito;
/*
import org.junit.Test;
import org.junit.runner.RunWith;

 */
import org.mockito.Mockito;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @InjectMocks
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;


    @Test
    void register() throws Exception{
        ClientController clientController=new ClientController();
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         /*
        when(clientService.register(any(" dede","dede "," dede","dede ","dede "," dede","ddd ")).thenReturn(true);

        ResponseEntity<Client> responseEntity = clientController.register(" dede","dede "," dede","dede ","dede "," dede","ddd ");

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
        */

    }

    @Test
    void getclients() {
    }
}