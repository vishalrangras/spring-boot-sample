package com.lithan.sb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lithan.sb.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
	
	<T>T findDtoedByAddressId(long id, Class<T> dto);
	<T>T findProjectedByAddressId(long id, Class<T> projection);
}
