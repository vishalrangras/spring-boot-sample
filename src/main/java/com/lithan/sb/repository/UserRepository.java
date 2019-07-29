package com.lithan.sb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lithan.sb.model.User;

public interface UserRepository extends JpaRepository<User, String>{
	
	<T>T findDtoedByUserName(String userName, Class<T> dto);
	<T>T findAllDtoedByRoleRoleCode(String roleCode, Class<T> dto);
	<T>List<T> findAllDtoedBy(Class<T> dto);
}