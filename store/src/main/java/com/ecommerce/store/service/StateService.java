package com.ecommerce.store.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.store.domain.State;
import com.ecommerce.store.record.StateRecord;
import com.ecommerce.store.repository.StateRepository;
import com.ecommerce.store.service.exception.EntityNotFoundException;

@Service
public class StateService {

	@Autowired
	private StateRepository stateRepository;
	
	@Transactional(readOnly = true)
	public List<StateRecord> findAll(){
		List<State> list = stateRepository.findAll();
		List<StateRecord> result = new ArrayList<>();
		list.forEach(x -> result.add(new StateRecord(x)));
		return result;
	}
	
	@Transactional(readOnly = true)
	public StateRecord findById(Long stateId){
		State entity = stateRepository.findById(stateId)
				.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		return new StateRecord(entity);
	}
}
