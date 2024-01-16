package com.sis.retrospective.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sis.retrospective.exception.GenericException;
import com.sis.retrospective.model.Feedback;
import com.sis.retrospective.model.GenericResponse;
import com.sis.retrospective.model.PartialFeedback;
import com.sis.retrospective.model.Retrospective;
import com.sis.retrospective.model.RetrospectiveListPage;
import com.sis.retrospective.service.RetrospectiveService;

import jakarta.validation.Valid;

@RestController
public class RetrospectiveController {

	@Autowired
	RetrospectiveService retrospectiveService;

	@PostMapping("/retrospectives")
	@ResponseStatus(HttpStatus.CREATED)
	public GenericResponse createRetrospective(@RequestBody @Valid Retrospective retrospective)
			throws GenericException {
		return retrospectiveService.createRetrospective(retrospective);
	}

	@GetMapping("/retrospectives")
	public RetrospectiveListPage getRetrospectiveByName(
			@RequestParam(required = false, defaultValue = "10") int pageSize,
			@RequestParam(required = false, defaultValue = "1") int currentPage) throws GenericException {
		return retrospectiveService.getRetrospectiveList(pageSize, currentPage);
	}

	@GetMapping("/retrospectives/{retrospectiveName}")
	public Retrospective getRetrospective(@PathVariable String retrospectiveName) throws GenericException {
		return retrospectiveService.getRetrospectiveByName(retrospectiveName);
	}

	@PostMapping("/retrospectives/{retrospectiveName}/feedback")
	@ResponseStatus(HttpStatus.CREATED)
	public GenericResponse provideFeedback(@PathVariable String retrospectiveName,
			@RequestBody @Valid Feedback feedback) throws GenericException {
		return retrospectiveService.addFeedback(feedback, retrospectiveName);
	}

	@GetMapping("/retrospectives/search")
	public RetrospectiveListPage searchRetrospective(@RequestParam LocalDate dateQuery) throws GenericException {
		return retrospectiveService.searchRetrospective(dateQuery);
	}
	
	@PatchMapping("/retrospectives/{retrospectiveName}/feedback/{feedbackId}")
	public GenericResponse updateFeedbackDetails(@PathVariable String retrospectiveName, @PathVariable int feedbackId,
			@RequestBody PartialFeedback feedback) throws GenericException {
		return retrospectiveService.updateFeedback(feedbackId, feedback, retrospectiveName);
	}

}
