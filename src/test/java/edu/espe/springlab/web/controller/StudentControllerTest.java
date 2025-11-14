package edu.espe.springlab.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    //Nombre: Johan Alomia
    @Autowired
    private MockMvc mockMvc;

    @Test
    void error404CuandoIdNoExiste() throws Exception {
        //Nombre: Johan Alomia
        mockMvc.perform(get("/api/students/9999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("No existe estudiante")));
    }

    @Test
    void InterceptorElapsed() throws Exception {
        //Nombre: Johan Alomia
        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(header().exists("X-Elapsed-Time"))
                .andExpect(header().string("X-Elapsed-Time", not(isEmptyOrNullString())));
    }
}
