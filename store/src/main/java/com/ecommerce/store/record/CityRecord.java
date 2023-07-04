package com.ecommerce.store.record;

import com.ecommerce.store.domain.City;
import com.ecommerce.store.domain.State;

public record CityRecord(Long id, String name, State state) {

	public CityRecord(City city) {
		this(city.getId(), city.getName(), city.getState());
	}
}


