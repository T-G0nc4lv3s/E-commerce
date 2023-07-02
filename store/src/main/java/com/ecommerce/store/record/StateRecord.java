package com.ecommerce.store.record;

import com.ecommerce.store.domain.State;

public record StateRecord(Long id, String name, String acronym) {

	public StateRecord(State state) {
		this(state.getId(), state.getName(), state.getAcronym());
	}
}
