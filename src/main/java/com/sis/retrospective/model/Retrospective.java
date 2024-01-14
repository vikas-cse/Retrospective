package com.sis.retrospective.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public class Retrospective {

	public Retrospective(String name, String summary, List<String> userList, LocalDate date) {
		this.name = name;
		this.summary = summary;
		this.userList = userList;
		this.date = date;
	}

	public Retrospective() {
		// TODO Auto-generated constructor stub
	}

	@NotEmpty
	private String name;

	private String summary;

	@NotNull
	private LocalDate date;

	@JsonProperty("users")
	@NotNull
	@NotEmpty
	private List<String> userList;

	@JsonProperty("feedbacks")
	@Null
	private List<Feedback> feedbacks;

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<String> getUserList() {
		return userList;
	}

	public void setUserList(List<String> userList) {
		this.userList = userList;
	}

}
