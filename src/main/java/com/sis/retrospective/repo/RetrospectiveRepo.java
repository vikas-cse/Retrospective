package com.sis.retrospective.repo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sis.retrospective.model.Retrospective;

/**
 * This class is added to save and retrieve retrospective details.
 */
@Component
public final class RetrospectiveRepo {

	private Map<String, Retrospective> retrospectiveMap = new HashMap<>();

	public Retrospective getRetrospectiveByName(String retrospectiveName) {
		return retrospectiveMap.get(retrospectiveName);
	}

	public Retrospective save(Retrospective retrospective) {
		retrospectiveMap.put(retrospective.getName(), retrospective);
		return retrospective;
	}

	public int getLength() {
		return retrospectiveMap.size();
	}

	public List<Retrospective> getList(int startIndex, int endIndex) {
		if (startIndex < getLength() && endIndex <= getLength()) {
			return new ArrayList<>(retrospectiveMap.values()).subList(startIndex, endIndex);
		}
		return null;
	}

	public List<Retrospective> getRetrospectiveByDate(LocalDate retrospectiveDate) {
		return retrospectiveMap.values().stream().filter(t -> t.getDate().equals(retrospectiveDate)).toList();
	}
}
