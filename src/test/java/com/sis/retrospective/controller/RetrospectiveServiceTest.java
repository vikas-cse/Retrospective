package com.sis.retrospective.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.sis.retrospective.model.Retrospective;
import com.sis.retrospective.model.RetrospectiveListPage;
import com.sis.retrospective.service.RetrospectiveService;

@SpringBootTest
@AutoConfigureMockMvc
public class RetrospectiveServiceTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	RetrospectiveService retrospectiveService;
	
	@InjectMocks
	RetrospectiveController retrospectiveController;

    @BeforeEach
    public void setUp() {
    }
    
    @Test
    public void TestGetRetrospectiveListWithMockedData() throws Exception {
    	
    	Retrospective retrospective = new Retrospective("test name", "testSummary", List.of("UserA", "UserB"), LocalDate.now());
    	RetrospectiveListPage retrospectiveListPage = new RetrospectiveListPage(1, 1, 1);
    	retrospectiveListPage.setRetrospectives(List.of(retrospective));
        when(retrospectiveService.getRetrospectiveList(10, 1)).thenReturn(retrospectiveListPage);
      
    	mockMvc.perform( MockMvcRequestBuilders
      	      .get("/retrospectives")
      	      .contentType(MediaType.APPLICATION_JSON)
      	      .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$.totalRecords").value("1"));
    }
    
}
