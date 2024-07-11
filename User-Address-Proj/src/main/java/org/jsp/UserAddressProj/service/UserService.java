package org.jsp.UserAddressProj.service;


import java.util.Optional;

import org.jsp.UserAddressProj.dao.UserDao;
import org.jsp.UserAddressProj.dto.ResponseStructure;
import org.jsp.UserAddressProj.dto.User;
import org.jsp.UserAddressProj.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		ResponseStructure<User> structure=new ResponseStructure<>();
		structure.setMessage("User Save Successfull");
		structure.setData(userDao.saveUser(user));
		structure.setStatuscode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<User>> updateUser(User user){
		Optional<User> recUser=userDao.findById(user.getId());
		if(recUser.isPresent()) {
			User dbUser=recUser.get();
			dbUser.setName(user.getName());
			dbUser.setPhone(user.getPhone());
			dbUser.setGender(user.getGender());
			dbUser.setAge(user.getAge());
			dbUser.setEmail(user.getEmail());
			dbUser.setPassword(user.getPassword());
			ResponseStructure<User> structure=new ResponseStructure<>();
			structure.setMessage("User Updated Successfully");
			structure.setData(userDao.saveUser(dbUser));
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
		}
		throw new UserNotFoundException("Invalid User Id");
	}
	
	public ResponseEntity<ResponseStructure<User>> findById(int id){
		ResponseStructure<User> structure=new ResponseStructure<>();
		Optional<User> dbUser=userDao.findById(id);
		if(dbUser.isPresent()) {
			structure.setMessage("User Found");
			structure.setData(dbUser.get());
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);
		}
		throw new UserNotFoundException("Invalid User Id");
	}
	
	public ResponseEntity<ResponseStructure<User>> verifyUser(long phone,String password){
		int id = 0;
		Optional<User> dbUser=userDao.findById(id);
		ResponseStructure<User> structure=new ResponseStructure<>();
		if(dbUser.isPresent()) {
			structure.setMessage("USer found");
			structure.setData(dbUser.get());
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);
		}
		throw new UserNotFoundException("Invalid Phone Number or Password");
	}
	
	public ResponseEntity<ResponseStructure<User>> verifyUser(String email,String password){
		Optional<User> dbMerchant=userDao.verifyUser(email, password);
		ResponseStructure<User> structure=new ResponseStructure<>();
		if(dbMerchant.isPresent()) {
			structure.setMessage("User found");
			structure.setData(dbMerchant.get());
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);
		}
		throw new UserNotFoundException("Invalid Email Id or Password");
	}
	
}


