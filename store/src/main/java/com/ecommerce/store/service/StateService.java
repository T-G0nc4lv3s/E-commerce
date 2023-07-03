package com.ecommerce.store.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.store.domain.State;
import com.ecommerce.store.dto.StateDTO;
import com.ecommerce.store.record.StateRecord;
import com.ecommerce.store.repository.StateRepository;
import com.ecommerce.store.service.exception.EntityNotFoundException;
import com.ecommerce.store.util.Formatter;

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
	
	@Transactional
	public StateRecord insertState(StateDTO dto) {
		State entiy = stateRepository.save(Formatter.stateInstance(dto));
		return new StateRecord(entiy);
	}
	
	@Transactional
	public void deleteStateById(Long stateId) {
		try {
			stateRepository.deleteById(stateId);
		} catch (IllegalArgumentException e) {
			throw new EntityNotFoundException(e.getMessage());
		}
	}
	
	@Transactional
	public StateRecord updateState(Long stateId, StateDTO dto) {
		try {
			State entity = stateRepository.getReferenceById(stateId);
			dto.setId(stateId);
			BeanUtils.copyProperties(dto, entity);
			entity = stateRepository.save(entity);
			return new StateRecord(entity);
			
		} catch (Exception e) {
			throw new EntityNotFoundException("Entity not found");
		}
	}
}
