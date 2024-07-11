package org.jsp.UserAddressProj.dao;


import java.util.Optional;

import org.jsp.UserAddressProj.dto.User;
import org.jsp.UserAddressProj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository userRepository;

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public Optional<User> findById(int id) {
		return userRepository.findById(id);
	}

	public Optional<User> verifyUser(long phone, String password) {
		return userRepository.verifyUser(phone, password);
	}

	public Optional<User> verifyUser(String email, String password) {
		return userRepository.verifyUser(email, password);
	}
	
}



