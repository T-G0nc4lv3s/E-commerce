package com.ecommerce.store.controller;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecommerce.store.dto.CityDTO;
import com.ecommerce.store.dto.CityMinDTO;
import com.ecommerce.store.service.CityService;

@RestController
@RequestMapping(value = "/cities")
public class CityController {

	@Autowired
	private CityService cityService;
	
	@GetMapping(value = "/{cityId}")
	public ResponseEntity<CityMinDTO> findById(@PathVariable Long cityId){
		CityMinDTO response = cityService.findById(cityId);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping
	public ResponseEntity<List<CityMinDTO>> findAll(){
		List<CityMinDTO> response = cityService.findAllClients();
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<CityMinDTO> insertCity(@RequestBody CityDTO dto){
		CityMinDTO response = cityService.insertCity(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("{/cityId}").buildAndExpand(response.getId()).toUri();
		return ResponseEntity.created(uri).body(response);
	}
	
	@PutMapping(value = "/{cityId}")
	public ResponseEntity<CityMinDTO> updateCity(@PathVariable Long cityId, @RequestBody CityDTO dto){
		CityMinDTO response = cityService.updateCity(cityId, dto);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "/{cityId}")
	public ResponseEntity<Object> deleteCity(@PathVariable Long cityId){
		cityService.deleteById(cityId);
		return ResponseEntity.noContent().build();	
				
	}
	
}
