package com.ecommerce.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.store.record.StateRecord;
import com.ecommerce.store.service.StateService;

@RestController
@RequestMapping(value = "/states")
public class StateController {

	@Autowired
	private StateService stateService;
	
	@GetMapping
	public ResponseEntity<List<StateRecord>> findall(){
		List<StateRecord> response = stateService.findAll();
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/{stateId}")
	public ResponseEntity<StateRecord> findById(@PathVariable Long stateId){
		StateRecord response = stateService.findById(stateId);
		return ResponseEntity.ok(response);
	}
}
