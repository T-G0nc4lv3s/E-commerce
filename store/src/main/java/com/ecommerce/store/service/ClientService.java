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
import com.ecommerce.store.dto.ClientInsertDTO;
import com.ecommerce.store.dto.ClientMinDTO;
import com.ecommerce.store.dto.ClientUpdateDTO;
import com.ecommerce.store.projections.ClientMinProjection;
import com.ecommerce.store.repository.AddressRepository;
import com.ecommerce.store.repository.CityRepository;
import com.ecommerce.store.repository.ClientRepository;
import com.ecommerce.store.service.exception.DatabaseException;
import com.ecommerce.store.service.exception.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Transactional(readOnly = true)
	public List<ClientMinDTO> findAll(){
		List<ClientMinProjection> result = clientRepository.findAllClients();
		return result.stream().map(item -> new ClientMinDTO(item)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public ClientMinDTO findById(Long clientId) {
		Client entity = clientRepository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ClientMinDTO(entity);
	}
	
	@Transactional
	public ClientMinDTO insertNewClient(ClientInsertDTO dto) {
		Long cityId = Long.valueOf(dto.getCityId());
		if(!cityRepository.existsById(cityId)) {
			throw new DatabaseException("Integrity violation, city does not exists");
		}
		
		Client entity = fromDTO(dto);
		entity = clientRepository.save(entity);
		
		for(Address item : entity.getAddresses() ) {
			item.setClient(entity);
		}
		
		addressRepository.saveAll(entity.getAddresses());
		return new ClientMinDTO(entity);
	}

	@Transactional
	public ClientMinDTO updateClient(Long clientId, ClientUpdateDTO dto) {
		
		try {
			Client entity = clientRepository.getReferenceById(clientId);
			BeanUtils.copyProperties(dto, entity);
			entity = clientRepository.save(entity);
			return new ClientMinDTO(entity);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Client not found, id: " + String.valueOf(clientId));
		}
		
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void deleteClientById(Long clientId) {
		if(!clientRepository.existsById(clientId)) {
			throw new ResourceNotFoundException("Client not found, id: " + String.valueOf(clientId));
		}
		clientRepository.deleteById(clientId);
	}
	
	private Client fromDTO(ClientInsertDTO dto) {
		Client obj = new Client();
		BeanUtils.copyProperties(dto, obj);
		obj.getPhones().add(dto.getPhone1());
		
		if(dto.getPhone2() != null) {
			obj.getPhones().add(dto.getPhone2());
		}
		
		if(dto.getPhone3() != null) {
			obj.getPhones().add(dto.getPhone3());
		}
		
		City city = new City();
		city.setId(Long.valueOf(dto.getCityId()));
		
		Address address = new Address(null, dto.getStreet(), dto.getNumber(),
				dto.getDistrict(), dto.getComplement(), dto.getZipCode(), null, city);
		obj.getAddresses().add(address);
		return obj;
	}
	
}
