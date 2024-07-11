package org.jsp.UserAddressProj.service;

import java.util.List;
import java.util.Optional;

import org.jsp.UserAddressProj.dao.AddressDao;
import org.jsp.UserAddressProj.dto.Address;
import org.jsp.UserAddressProj.dto.User;
import org.jsp.UserAddressProj.exception.AddressNotFoundException;
import org.jsp.UserAddressProj.exception.UserNotFoundException;
import org.jsp.UserAddressProj.dto.ResponseStructure;
import org.jsp.UserAddressProj.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<Address>> addAddress(Address address, int user_id) {
		ResponseStructure<Address> structure = new ResponseStructure<>();
		Optional<User> dbUser = userDao.findById(user_id);
		if (dbUser.isPresent()) {
			User user = dbUser.get();
			address.setUser(user);
			user.getAddresses().add(address);
			structure.setMessage("address Added");
			structure.setData(addressDao.addAddress(address));
			structure.setStatuscode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.CREATED);
		}
		throw new UserNotFoundException("address is Not Added...! Invalid user Id");
	}

	public ResponseEntity<ResponseStructure<Address>> updateAddress(Address address) {
		ResponseStructure<Address> structure = new ResponseStructure<>();
		Optional<Address> recAddress = addressDao.findById(address.getId());
		if (recAddress.isPresent()) {
			Address dbAddress = recAddress.get();
			dbAddress.setBname(address.getBname());
			dbAddress.setLandmark(address.getLandmark());
			dbAddress.setArea(address.getArea());
			dbAddress.setCity(address.getCity());
			dbAddress.setState(address.getState());
			dbAddress.setCountry(address.getCountry());
			dbAddress.setPincode(address.getPincode());
			structure.setData(addressDao.addAddress(dbAddress));
			structure.setMessage("Update Address Successfull");
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.ACCEPTED);
		}
		throw new AddressNotFoundException("Updated UnSuccessfull..! Invalid address Id");
	}

	public ResponseEntity<ResponseStructure<List<Address>>> findByUserId(int user_id) {
		ResponseStructure<List<Address>> structure = new ResponseStructure<>();
		List<Address> addresses=addressDao.findByUserId(user_id);
		if(addresses.size()>0) {
			structure.setMessage("Address Found");
			structure.setData(addressDao.findByUserId(user_id));
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Address>>>(structure,HttpStatus.OK);
		}
		throw new AddressNotFoundException("Invalid Merchant Id");
	}
	
	
	
	public ResponseEntity<ResponseStructure<List<Address>>> findByCountry(String country) {
		ResponseStructure<List<Address>> structure = new ResponseStructure<>();
		List<Address> addresses=addressDao.findByCountry(country);
		if(addresses.size()>0) {
			structure.setMessage("Product Found");
			structure.setData(addressDao.findByCountry(country));
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Address>>>(structure,HttpStatus.OK);
		}
		throw new AddressNotFoundException("Invalid Category");
	}
	
	public ResponseEntity<ResponseStructure<Address>> findById(int id){
		ResponseStructure<Address> structure=new ResponseStructure<>();
		Optional<Address> products=addressDao.findById(id);
		if(products.isPresent()) {
			structure.setMessage("Product Found");
			structure.setData(products.get());
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Address>>(structure,HttpStatus.OK);
		}
		throw new UserNotFoundException("Invalid product Id");
	}
	
	
}


