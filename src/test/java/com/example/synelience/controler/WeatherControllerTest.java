package com.example.synelience.controler;

import com.example.synelience.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
@AutoConfigureMockMvc
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @Test
    public void testHealthCheck() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/health"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("Server up and running."));
    }

    @Test
    public void testWeather_WithNoApiKey_ShouldReturn401() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weather?city=lisbon"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void testWeather_WithApiKeyValid_ShouldReturn200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weather?city=lisbon&apikey=my-valid-api-key"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testWeather_WithApiKeyInvalid_ShouldReturn200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weather?city=lisbon&apikey=my-no-valid-api-key"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

}
