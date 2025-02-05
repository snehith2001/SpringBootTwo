package org.jsp.UserAddressProj.controller;

import org.jsp.UserAddressProj.dto.ResponseStructure;
import org.jsp.UserAddressProj.dto.User;
import org.jsp.UserAddressProj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@GetMapping("/users")
	public ResponseEntity<ResponseStructure<User>> findById(@RequestParam int id) {
		return userService.findById(id);
	}
	
	@PutMapping("/users")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	@PostMapping("/users/verify-phone")
	public ResponseEntity<ResponseStructure<User>> verifyUser(@RequestParam long phone,@RequestParam String password) {
		return userService.verifyUser(phone, password);
	}
	
	@PostMapping("/users/verify-email")
	public ResponseEntity<ResponseStructure<User>> verifyUser(@RequestParam String email,@RequestParam String password) {
		return userService.verifyUser(email, password);
	}
	

}


