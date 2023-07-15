package com.ecommerce.store.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.store.domain.Address;
import com.ecommerce.store.domain.City;
import com.ecommerce.store.domain.Client;
import com.ecommerce.store.dto.AddressDTO;
import com.ecommerce.store.dto.AddressMinDTO;
import com.ecommerce.store.projections.AddressMinProjection;
import com.ecommerce.store.repository.AddressRepository;
import com.ecommerce.store.repository.CityRepository;
import com.ecommerce.store.repository.ClientRepository;
import com.ecommerce.store.service.exception.EntityNotFoundException;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional(readOnly = true)
	public List<AddressMinDTO> findAll(){
		List<AddressMinProjection> result = addressRepository.findAllAddresses();
		return result.stream().map(item -> new AddressMinDTO(item)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public AddressMinDTO findById(Long addressId){
		Address result = addressRepository.findById(addressId).orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		return new AddressMinDTO(result);
	}
	
	
	@Transactional
	public AddressMinDTO insertAddress(AddressDTO dto) {
		try {	
			Address obj = dtoToAddress(dto);
			obj = addressRepository.save(obj);
			return new AddressMinDTO(obj);
		} catch (Exception e) {
			throw new EntityNotFoundException("Entity not found");
		}
	}
	
	@Transactional
	public AddressMinDTO updateAddress(Long addressId, AddressDTO dto) {
		try {
			Address entity = addressRepository.getReferenceById(addressId);
			Address obj = dtoToAddress(dto);
			BeanUtils.copyProperties(obj, entity);
			entity.setId(addressId);
			entity = addressRepository.save(entity);
			return new AddressMinDTO(entity);
		} catch (Exception e) {
			throw new EntityNotFoundException("Entity not found");
		}
		
	}
	
	@Transactional
	public void deleteAddressById(Long addressId) {
		try {
			addressRepository.deleteById(addressId);
		} catch (IllegalArgumentException e) {
			throw new EntityNotFoundException("Entity not found");
		}
	}
	
	private Address dtoToAddress(AddressDTO dto) throws Exception{
		Address entity = new Address();
		BeanUtils.copyProperties(dto, entity);
		City city = cityRepository.getReferenceById(Long.valueOf(dto.getCityId()));
		Client client = (Client) clientRepository.getReferenceById(Long.valueOf(dto.getClientId()));
		entity.setCity(city);
		entity.setClient(client);
		return entity;
	}
	
}
