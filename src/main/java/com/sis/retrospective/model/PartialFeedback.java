package com.sis.retrospective.model;

import com.fasterxml.jackson.annotation.JsonRootName;

import jakarta.validation.constraints.NotNull;

@JsonRootName("feedback")
public class PartialFeedback {

	@NotNull
	private FeedbackType type;

	@NotNull
	private String body;
	
	public PartialFeedback(FeedbackType type, String body) {
		this.type = type;
		this.body = body;
	}

	public FeedbackType getType() {
		return type;
	}

	public void setType(FeedbackType type) {
		this.type = type;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
