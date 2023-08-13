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

import com.ecommerce.store.dto.AddressDTO;
import com.ecommerce.store.service.AddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/addresses")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@GetMapping
	public ResponseEntity<List<AddressDTO>> findAll(){
		List<AddressDTO> response = addressService.findAll();
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/{addressId}")
	public ResponseEntity<AddressDTO> findById(@PathVariable Long addressId){
		AddressDTO response = addressService.findById(addressId);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<AddressDTO> insertAddress(@RequestBody @Valid AddressDTO dto){
		AddressDTO response = addressService.insertAddress(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("{/addressId}").buildAndExpand(response.getId()).toUri();
		return ResponseEntity.created(uri).body(response);
	}
	
	@PutMapping(value = "/{addressId}")
	public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long addressId, @RequestBody @Valid AddressDTO dto){
		AddressDTO response = addressService.updateAddress(addressId, dto);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "/{addressId}")
	public ResponseEntity<Object> deleteAddressByID(@PathVariable Long addressId){
		addressService.deleteAddressById(addressId);
		return ResponseEntity.noContent().build();
	}
}
