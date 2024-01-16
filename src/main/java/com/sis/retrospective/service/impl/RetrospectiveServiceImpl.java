package com.sis.retrospective.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sis.retrospective.repo.FeedbackRepo;
import com.sis.retrospective.repo.RetrospectiveRepo;
import com.sis.retrospective.exception.GenericException;
import com.sis.retrospective.model.Feedback;
import com.sis.retrospective.model.GenericResponse;
import com.sis.retrospective.model.PartialFeedback;
import com.sis.retrospective.model.Retrospective;
import com.sis.retrospective.model.RetrospectiveListPage;
import com.sis.retrospective.service.RetrospectiveService;

@Service
public class RetrospectiveServiceImpl implements RetrospectiveService {

	Logger LOGGER = LoggerFactory.getLogger(RetrospectiveServiceImpl.class);

	@Autowired
	private RetrospectiveRepo retrospectiveRepo;

	@Autowired
	private FeedbackRepo feedbackRepo;

	@Override
	public GenericResponse createRetrospective(Retrospective retrospective) throws GenericException {

		if (retrospectiveRepo.getRetrospectiveByName(retrospective.getName()) != null) {
			String errorMessage = String.format(
					"Retrospective name already exists with name '%s'. Please use a different name for retrospective.",
					retrospective.getName());
			LOGGER.error(errorMessage);
			throw new GenericException(errorMessage);
		}

		retrospectiveRepo.save(retrospective);

		return new GenericResponse(String.format("Retrospective Added with name: '%s'", retrospective.getName()));
	}

	@Override
	public RetrospectiveListPage getRetrospectiveList(int pageSize, int currentPage) {
		int totalRecords = retrospectiveRepo.getLength();
		int startIndex = (currentPage - 1) * pageSize;
		int endIndex = Math.min((currentPage * pageSize), totalRecords);

		LOGGER.debug("Getting retrospective list with records: firstRecord:{}, upto lastRecord:{} ", startIndex + 1,
				endIndex);
		RetrospectiveListPage retrospectiveListPage = new RetrospectiveListPage(currentPage, pageSize, totalRecords);
		retrospectiveListPage.setRetrospectives(retrospectiveRepo.getList(startIndex, endIndex));

		return retrospectiveListPage;
	}

	@Override
	public RetrospectiveListPage searchRetrospective(LocalDate date) {
		List<Retrospective> list = retrospectiveRepo.getRetrospectiveByDate(date);

		RetrospectiveListPage retrospectiveListPage = new RetrospectiveListPage(1, list.size(), list.size());
		retrospectiveListPage.setRetrospectives(list);

		return retrospectiveListPage;
	}

	@Override
	public GenericResponse addFeedback(Feedback feedback, String retrospectiveName) throws GenericException {
		Retrospective retrospective = retrospectiveRepo.getRetrospectiveByName(retrospectiveName);
		if (retrospective == null) {
			throw new GenericException("Retrospective name doesn't exist. Please provide existing retrospective name.");
		}

		if (!retrospective.getUserList().contains(feedback.getUserName())) {
			throw new GenericException("User name doesn't exist in Retrospective. Please provide existing user name.");
		}
		feedback = feedbackRepo.save(feedback, retrospective);

		return new GenericResponse(String.format("Feedback Added for Retrospective name: %s with id %s",
				retrospectiveName, feedback.getId()));

	}
	
	@Override
	public GenericResponse updateFeedback(int feedbackId, PartialFeedback feedback, String retrospectiveName)
			throws GenericException {
		Retrospective retrospective = retrospectiveRepo.getRetrospectiveByName(retrospectiveName);
		if (retrospective == null) {
			throw new GenericException("Retrospective name doesn't exist. Please provide existing retrospective name.");
		}

		if (retrospective.getFeedbacks() == null || retrospective.getFeedbacks().size() < feedbackId) {
			throw new GenericException("Feedback doesn't exist. Please provide existing feedbackId.");
		}

		feedbackRepo.update(feedbackId, feedback, retrospective);

		return new GenericResponse(
				String.format("Feedback Updated for Retrospective name: %s with id %s", retrospectiveName, feedbackId));
	}

	@Override
	public Retrospective getRetrospectiveByName(String name) throws GenericException {
		Retrospective retrospective = retrospectiveRepo.getRetrospectiveByName(name);
		if (retrospective == null) {
			throw new GenericException("Retrospective name doesn't exist. Please provide existing retrospective name.");
		}
		return retrospective;
	}
	

}
