package com.sis.retrospective.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sis.retrospective.model.Feedback;
import com.sis.retrospective.model.FeedbackType;
import com.sis.retrospective.model.Retrospective;
import com.sis.retrospective.service.RetrospectiveService;

@SpringBootTest
@AutoConfigureMockMvc
public class RetrospectiveControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@Mock
	RetrospectiveService retrospectiveService;
	
	@InjectMocks
	RetrospectiveController retrospectiveController;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void createRetrospectiveWithoutDate() throws Exception 
    {
    	mockMvc.perform( MockMvcRequestBuilders
      	      .post("/retrospectives")
      	      .content(asJsonString(new Retrospective("test name", "testSummary", List.of("UserA", "UserB"), null)))
      	      .contentType(MediaType.APPLICATION_JSON)
      	      .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string("Validation failed due to below reasons : \n"
            		+ "NotNull.retrospective.date must not be null\n"));
    }
    
    @Test
    public void createRetrospectiveWithEmptyUserList() throws Exception 
    {
    	mockMvc.perform( MockMvcRequestBuilders
      	      .post("/retrospectives")
      	      .content(asJsonString(new Retrospective("test name", "testSummary", new ArrayList<>(), LocalDate.now())))
      	      .contentType(MediaType.APPLICATION_JSON)
      	      .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string("Validation failed due to below reasons : \n"
            		+ "NotEmpty.retrospective.userList must not be empty\n"));
    }
    
    @Test
    public void createRetrospectiveSuccessfully() throws Exception 
    {
    	mockMvc.perform( MockMvcRequestBuilders
      	      .post("/retrospectives")
      	      .content(asJsonString(new Retrospective("test name", "testSummary", List.of("UserA", "UserB"), LocalDate.now())))
      	      .contentType(MediaType.APPLICATION_JSON)
      	      .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.message").value("Retrospective Added with name: 'test name'"));
    }
    
    @Test
    public void createFeedbackWithoutUsername() throws Exception 
    {
    	mockMvc.perform( MockMvcRequestBuilders
      	      .post("/retrospectives/testName/feedback")
      	      .content(asJsonString(new Feedback(null, FeedbackType.positive, "dummy feedback added.")))
      	      .contentType(MediaType.APPLICATION_JSON)
      	      .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().string("Validation failed due to below reasons : \n"
            		+ "NotNull.feedback.userName must not be null\n"));
    }
    
    public static String asJsonString(final Object obj) {
        try {
        	ObjectMapper objectMapper = new ObjectMapper();
        	objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
