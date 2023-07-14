package com.ecommerce.store.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.store.domain.City;
import com.ecommerce.store.domain.State;
import com.ecommerce.store.dto.CityDTO;
import com.ecommerce.store.dto.CityMinDTO;
import com.ecommerce.store.projections.CityMinProjection;
import com.ecommerce.store.repository.CityRepository;
import com.ecommerce.store.repository.StateRepository;
import com.ecommerce.store.service.exception.EntityNotFoundException;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Transactional(readOnly = true)
	public CityMinDTO findById(Long cityId) {
		City entity = cityRepository.findById(cityId)
				.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		return new CityMinDTO(entity);
	}
	
	@Transactional
	public List<CityMinDTO> findAllClients(){
		List<CityMinProjection> list = cityRepository.findAllCities();
		return list.stream().map(item -> new CityMinDTO(item)).collect(Collectors.toList());
	}
	
	@Transactional
	public void deleteCityById(Long cityId) {
		try {
			cityRepository.deleteById(cityId);
		} catch (IllegalArgumentException e) {
			throw new EntityNotFoundException("Entity not found");
		}
	}
	
	@Transactional
	public CityMinDTO insertCity(CityDTO dto) {
		try {
			City entity = new City();
			entity = dtoToCity(entity, dto);
			entity = cityRepository.save(entity);
			return new CityMinDTO(entity);
		} catch (Exception e) {
			throw new EntityNotFoundException("entity not found");
		}
		
	}
	
	@Transactional
	public CityMinDTO updateCity(Long cityId, CityDTO dto) {
		
		try {
			City entity = cityRepository.getReferenceById(cityId);
			entity = dtoToCity(entity, dto);
			entity.setId(cityId);
			entity = cityRepository.save(entity);
			return new CityMinDTO(entity);
		} catch (Exception e) {
			throw new EntityNotFoundException("Id not found");
		}
	}
	
	private City dtoToCity(City city, CityDTO cityDTO) throws Exception{
		BeanUtils.copyProperties(cityDTO, city);
		State state = stateRepository.getReferenceById(Long.valueOf(cityDTO.getStateId()));
		city.setState(state);
		return city;
	}
	
}
