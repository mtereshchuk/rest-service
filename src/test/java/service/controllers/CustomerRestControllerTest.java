package service.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import service.Boot;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Boot.class)
@WebAppConfiguration
public class CustomerRestControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void customerNotFound() throws Exception {
        mockMvc.perform(get("/4")).andExpect(status().isNotFound());
    }

    @Test
    public void applicationNotFound() throws Exception {
        mockMvc.perform(get("/0")).andExpect(status().isNotFound());
    }

    private void lastApplicationOfNumber(int number) throws Exception {
        mockMvc.perform(get("/" + number))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(number * 10 + number)))
                .andExpect(jsonPath("$.name", is("Product#" + (number * 10 + number))))
                .andExpect(jsonPath("$.customerId", is(number)));
    }

    @Test
    public void lastApplicationOf1() throws Exception {
        lastApplicationOfNumber(1);
    }

    @Test
    public void lastApplicationOf2() throws Exception {
        lastApplicationOfNumber(2);
    }

    @Test
    public void lastApplicationOf3() throws Exception {
        lastApplicationOfNumber(3);
    }
}