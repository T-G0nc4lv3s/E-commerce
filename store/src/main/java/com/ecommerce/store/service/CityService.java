package com.ecommerce.store.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.store.domain.City;
import com.ecommerce.store.dto.CityDTO;
import com.ecommerce.store.record.CityRecord;
import com.ecommerce.store.repository.CityRepository;
import com.ecommerce.store.service.exception.EntityNotFoundException;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
	@Transactional(readOnly = true)
	public CityRecord findById(Long cityId) {
		City entity = cityRepository.findById(cityId)
				.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		return new CityRecord(entity);
	}
	
	@Transactional(readOnly = true)
	public List<CityRecord> findAll() {
		List<City> list = cityRepository.findAll();
		List<CityRecord> result = new ArrayList<>();
		list.forEach(x -> result.add(new CityRecord(x)));
		return result;
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
	public CityRecord insertCity(CityDTO dto) {
		City entity = new City();
		BeanUtils.copyProperties(dto, entity);
		entity = cityRepository.save(entity);
		return new CityRecord(entity);
	}
	
	@Transactional
	public CityRecord updateCity(Long cityId, CityDTO dto) {
		
		try {
			City entity = cityRepository.getReferenceById(cityId);
			dto.setId(cityId);
			BeanUtils.copyProperties(dto, entity);
			entity = cityRepository.save(entity);
			return new CityRecord(entity);
		} catch (Exception e) {
			throw new EntityNotFoundException("Id not found");
		}
	}
}
