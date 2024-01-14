package com.sis.retrospective.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RetrospectiveListPage {
	int currentPage;

	int pageSize;

	int totalRecords;

	@JsonProperty("retrospectives")
	List<Retrospective> retrospectives;

	public RetrospectiveListPage(int currentPage, int pageSize, int totalRecords) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalRecords = totalRecords;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<Retrospective> getRetrospectives() {
		return retrospectives;
	}

	public void setRetrospectives(List<Retrospective> retrospectives) {
		this.retrospectives = retrospectives;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

}
