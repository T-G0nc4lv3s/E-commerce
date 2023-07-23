package com.ecommerce.store.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.store.domain.City;
import com.ecommerce.store.domain.State;
import com.ecommerce.store.dto.CityDTO;
import com.ecommerce.store.dto.CityMinDTO;
import com.ecommerce.store.repository.CityRepository;
import com.ecommerce.store.repository.StateRepository;
import com.ecommerce.store.service.exception.DatabaseException;
import com.ecommerce.store.service.exception.ResourceNotFoundException;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Transactional(readOnly = true)
	public CityMinDTO findById(Long cityId) {
		City entity = cityRepository.findById(cityId)
				.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new CityMinDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public List<CityMinDTO> findAllClients(){
		List<City> list = cityRepository.findAllCities();
		return list.stream().map(item -> new CityMinDTO(item)).collect(Collectors.toList());
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void deleteById(Long cityId) {
		if(!cityRepository.existsById(cityId)) {
			throw new ResourceNotFoundException("City id not found " + String.valueOf(cityId));
		}
		
		try {
			cityRepository.deleteById(cityId);
			
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
		
	}
	
	@Transactional
	public CityMinDTO insertCity(CityDTO dto) {
		Long stateId = Long.valueOf(dto.getStateId());
		State state = new State();
		
		City entity = new City();
		BeanUtils.copyProperties(dto, entity);
		
		if(!stateRepository.existsById(stateId)) {
			throw new DatabaseException("State id not found: " + dto.getStateId());
		}
		
		state.setId(stateId);
		entity.setState(state);
		entity = cityRepository.save(entity);
		return new CityMinDTO(entity);
	}
	
	@Transactional
	public CityMinDTO updateCity(Long cityId, CityDTO dto) {
		Long stateId = Long.valueOf(dto.getStateId());
		State state = new State();
		
		if(!stateRepository.existsById(stateId)) {
			throw new DatabaseException("State id not found: " + dto.getStateId());
		}
		state.setId(stateId);
		
		
		try {
			City city = cityRepository.getReferenceById(cityId);
			BeanUtils.copyProperties(dto, city);
			city.setState(state);
			city.setId(cityId);
			cityRepository.save(city);
			return new CityMinDTO(city);
		} catch (Exception e) {
			throw new ResourceNotFoundException("City not found id: " + String.valueOf(dto.getId()));
		}
	}
}
