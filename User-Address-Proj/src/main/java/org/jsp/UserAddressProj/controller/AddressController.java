package org.jsp.UserAddressProj.controller;


import java.util.List;

import org.jsp.UserAddressProj.dto.ResponseStructure;
import org.jsp.UserAddressProj.dto.Address;
import org.jsp.UserAddressProj.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AddressController {
	@Autowired
	private AddressService addressService;
	
	@PostMapping("/address/{id}")
	public ResponseEntity<ResponseStructure<Address>> addAddress(@RequestBody Address address,@PathVariable int id) {
		return addressService.addAddress(address, id);
	}
	
	@PutMapping("/address")
	public ResponseEntity<ResponseStructure<Address>> updateAddress(@RequestBody Address address) {
		return addressService.updateAddress(address);
	}
	
	@GetMapping("/address/ByUserId/{user_id}")
	public ResponseEntity<ResponseStructure<List<Address>>> findByUserId(@PathVariable int user_id) {
		return addressService.findByUserId(user_id);
	}
	
	
	@GetMapping("/address/ByCountry/{country}")
	public ResponseEntity<ResponseStructure<List<Address>>> findByCountry(@PathVariable String country) {
		return addressService.findByCountry(country);
	}
	
	
	@GetMapping("/address/{id}")
	public ResponseEntity<ResponseStructure<Address>> findById(@PathVariable int id) {
		return addressService.findById(id);
}
}

