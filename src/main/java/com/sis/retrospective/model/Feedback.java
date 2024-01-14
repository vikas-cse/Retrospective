package com.sis.retrospective.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

import jakarta.validation.constraints.NotNull;

@JsonRootName("feedback")
public class Feedback {
	
	@JsonIgnore
	private int id;

	@NotNull
	private String userName;
	@JsonIgnore
	private String retrospectiveName;

	@NotNull
	private FeedbackType type;

	@NotNull
	private String body;
	
	public Feedback(String userName, FeedbackType type, String body) {
		this.userName = userName;
		this.type = type;
		this.body = body;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRetrospectiveName() {
		return retrospectiveName;
	}

	public void setRetrospectiveName(String retrospectiveName) {
		this.retrospectiveName = retrospectiveName;
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
