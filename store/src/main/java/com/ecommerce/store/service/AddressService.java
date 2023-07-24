package com.ecommerce.store.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.store.domain.Address;
import com.ecommerce.store.domain.City;
import com.ecommerce.store.domain.Client;
import com.ecommerce.store.dto.AddressDTO;
import com.ecommerce.store.projections.AddressMinProjection;
import com.ecommerce.store.repository.AddressRepository;
import com.ecommerce.store.repository.CityRepository;
import com.ecommerce.store.repository.ClientRepository;
import com.ecommerce.store.service.exception.DatabaseException;
import com.ecommerce.store.service.exception.ResourceNotFoundException;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional(readOnly = true)
	public List<AddressDTO> findAll(){
		List<AddressMinProjection> result = addressRepository.findAllAddresses();
		return result.stream().map(item -> new AddressDTO(item)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public AddressDTO findById(Long addressId){
		Address result = addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new AddressDTO(result);
	}
	
	
	@Transactional
	public AddressDTO insertAddress(AddressDTO dto) {
		Long clientId = Long.valueOf(dto.getClientId());
		Long cityId = Long.valueOf(dto.getCityId());
		
		if(!clientRepository.existsById(clientId)) {
			throw new DatabaseException("Integrity violation, client does not exists");
		}
		else if (!cityRepository.existsById(cityId)) {
			throw new DatabaseException("Integrity violation, city does not exists");
		}
		
		
		Address obj = fromDTO(dto);
		obj = addressRepository.save(obj);
		return new AddressDTO(obj);
	}

	@Transactional
	public AddressDTO updateAddress(Long addressId, AddressDTO dto) {
		Long clientId = Long.valueOf(dto.getClientId());
		Long cityId = Long.valueOf(dto.getCityId());
		
		if(!clientRepository.existsById(clientId)) {
			throw new DatabaseException("Integrity violation, client not found: " + dto.getClientId());
		}
		else if (!cityRepository.existsById(cityId)) {
			throw new DatabaseException("Integrity violation, city not found: " + dto.getCityId());
		}
		
		try {
			Address entity = addressRepository.getReferenceById(addressId);
			Address obj = fromDTO(dto);
			BeanUtils.copyProperties(obj, entity);
			entity.setId(addressId);
			entity = addressRepository.save(entity);
			return new AddressDTO(entity);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Address not found, id: " + String.valueOf(addressId));
		}
		
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void deleteAddressById(Long addressId) {
		if(!addressRepository.existsById(addressId)) {
			throw new ResourceNotFoundException("Address not found, id: " + String.valueOf(addressId));
		}
		addressRepository.deleteById(addressId);
	}
	
	private Address fromDTO(AddressDTO dto) {
		Address address = new Address();
		Client client = new Client();
		City city = new City();
		BeanUtils.copyProperties(dto, address);
		client.setId(Long.valueOf(dto.getClientId()));
		city.setId(Long.valueOf(dto.getCityId()));
		address.setCity(city);
		address.setClient(client);
		return address;
 	}
}
