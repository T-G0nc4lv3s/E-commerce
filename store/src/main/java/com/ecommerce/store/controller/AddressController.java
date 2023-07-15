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
import com.ecommerce.store.dto.AddressMinDTO;
import com.ecommerce.store.service.AddressService;

@RestController
@RequestMapping(value = "/addresses")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@GetMapping
	public ResponseEntity<List<AddressMinDTO>> findAll(){
		List<AddressMinDTO> response = addressService.findAll();
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/{addressId}")
	public ResponseEntity<AddressMinDTO> findById(@PathVariable Long addressId){
		AddressMinDTO response = addressService.findById(addressId);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<AddressMinDTO> insertAddress(@RequestBody AddressDTO dto){
		AddressMinDTO response = addressService.insertAddress(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("{/addressId}").buildAndExpand(response.getId()).toUri();
		return ResponseEntity.created(uri).body(response);
	}
	
	@PutMapping(value = "/{addressId}")
	public ResponseEntity<AddressMinDTO> updateAddress(@PathVariable Long addressId, @RequestBody AddressDTO dto){
		AddressMinDTO response = addressService.updateAddress(addressId, dto);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "/{addressId}")
	public ResponseEntity<Object> deleteAddressByID(@PathVariable Long addressId){
		addressService.deleteAddressById(addressId);
		return ResponseEntity.noContent().build();
	}
}
