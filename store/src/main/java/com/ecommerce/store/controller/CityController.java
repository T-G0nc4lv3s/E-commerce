package com.ecommerce.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.store.dto.CityDTO;
import com.ecommerce.store.record.CityRecord;
import com.ecommerce.store.service.CityService;

@RestController
@RequestMapping(value = "/cities")
public class CityController {

	@Autowired
	private CityService cityService;
	
	@GetMapping(value = "/{cityId}")
	public ResponseEntity<CityRecord> findById(@PathVariable Long cityId){
		CityRecord response = cityService.findById(cityId);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping
	public ResponseEntity<List<CityRecord>> findAll(){
		List<CityRecord> response = cityService.findAll();
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<CityRecord> insertCity(@RequestBody CityDTO dto){
		CityRecord response = cityService.insertCity(dto);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping(value = "/{cityId}")
	public ResponseEntity<CityRecord> updateCity(@PathVariable Long cityId, @RequestBody CityDTO dto){
		CityRecord response = cityService.updateCity(cityId, dto);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "/{cityId}")
	public ResponseEntity<Object> deleteCity(@PathVariable Long cityId){
		cityService.deleteCityById(cityId);
		return ResponseEntity.noContent().build();	
				
	}
	
}
