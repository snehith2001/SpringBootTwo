package org.jsp.UserAddressProj.dao;


import java.util.List;
import java.util.Optional;

import org.jsp.UserAddressProj.dto.Address;
import org.jsp.UserAddressProj.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDao {
	@Autowired
	private AddressRepository addressRepository;

	public Address addAddress(Address address) {
		return addressRepository.save(address);
	}
	
	
	public List<Address> findByCountry(String country){
		return addressRepository.findByCountry(country);
	}
	
	public List<Address> findByUserId(int user_id){
		return addressRepository.findByUserId(user_id);
	}
	
	public Optional<Address> findById(int id){
		return addressRepository.findById(id);
	}
	
}

