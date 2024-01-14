package com.bestmatch.BestMatch.integration;

import com.bestmatch.BestMatch.controller.RestaurantController;
import com.bestmatch.BestMatch.dto.RestaurantDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(RestaurantController.class)
@ComponentScan(basePackages = {"com.bestmatch.BestMatch.service", "com.bestmatch.BestMatch.repository", "com.bestmatch.BestMatch.config"})
class RestaurantMatcherIntegrationTest {
    /*@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findBestMatch_ValidInput_ReturnsOk() throws Exception {
        // Prepare a valid RestaurantDTO for testing
        RestaurantDTO validDto = RestaurantDTO.builder().build();
        validDto.setName("Delicious");

        mockMvc.perform(MockMvcRequestBuilders.post("/findMatch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                // Add more assertions based on your specific expectations
                // Example: .andExpect(MockMvcResultMatchers.jsonPath("$.someField").value("expectedValue"))
                .andReturn();
    }

    @Test
    void findBestMatch_InvalidInput_ReturnsBadRequest() throws Exception {
        // Prepare an invalid RestaurantDTO for testing
        RestaurantDTO invalidDto = RestaurantDTO.builder().build();
        // Set invalid properties as needed

        // Perform the request and assert the response
        mockMvc.perform(MockMvcRequestBuilders.post("/findMatch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                // Add more assertions based on your specific expectations
                .andReturn();
    }*/

}
