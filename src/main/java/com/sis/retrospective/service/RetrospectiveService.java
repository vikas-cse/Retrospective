package com.sis.retrospective.service;

import java.time.LocalDate;

import com.sis.retrospective.exception.GenericException;
import com.sis.retrospective.model.Feedback;
import com.sis.retrospective.model.GenericResponse;
import com.sis.retrospective.model.PartialFeedback;
import com.sis.retrospective.model.Retrospective;
import com.sis.retrospective.model.RetrospectiveListPage;

public interface RetrospectiveService {

	public GenericResponse createRetrospective(Retrospective retrospective) throws GenericException;

	public RetrospectiveListPage getRetrospectiveList(int pageSize, int currentPage);

	public RetrospectiveListPage searchRetrospective(LocalDate date);

	public GenericResponse addFeedback(Feedback feedback, String retrospectiveName) throws GenericException;
	
	public GenericResponse updateFeedback(int feedbackId, PartialFeedback feedback, String retrospectiveName)
			throws GenericException;

	public Retrospective getRetrospectiveByName(String name) throws GenericException;

}
