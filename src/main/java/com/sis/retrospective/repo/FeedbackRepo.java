package com.sis.retrospective.repo;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.sis.retrospective.model.Feedback;
import com.sis.retrospective.model.PartialFeedback;
import com.sis.retrospective.model.Retrospective;

@Component
public final class FeedbackRepo {

	public Feedback save(Feedback feedback, Retrospective retrospective) {
		if (retrospective.getFeedbacks() == null) {
			retrospective.setFeedbacks(new ArrayList<>());
		}
		feedback.setId(retrospective.getFeedbacks().size() + 1);
		retrospective.getFeedbacks().add(feedback);
		return feedback;
	}

	public void update(int feedbackId, PartialFeedback feedback, Retrospective retrospective) {
		Feedback oldFeedback = retrospective.getFeedbacks().get(feedbackId - 1);
		oldFeedback.setBody(feedback.getBody());
		oldFeedback.setType(feedback.getType());
	}

}
