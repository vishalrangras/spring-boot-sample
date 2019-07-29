package com.lithan.sb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lithan.sb.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
	
	<T>List<T> findAllDtoedByUserUserName(String userName, Class<T> projection);
	<T>List<T> findAllProjectedByUserUserName(String userName, Class<T> projection);

}