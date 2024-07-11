package org.jsp.UserAddressProj.repository;

import java.util.List;

import org.jsp.UserAddressProj.dto.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	
	@Query("select u.addresses from User u where u.id=?1")
	List<Address> findByUserId(int user_id);
	
	@Query("select a from Address a where a.country=?1")
	List<Address> findByCountry(String country);
		
}


