package com.kketter.example.codeFellowship;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CodeFellowshipControllerTest {
    @Autowired
    CodeFellowshipController codeFellowshipController;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testControllerIsAutowired() {
        assertNotNull(codeFellowshipController);
    }

    @Test
    public void testRequestToRootGivesCodeFellowship() throws Exception {
        mockMvc.perform(get("/home")).andExpect(view().name("home"));
    }


}