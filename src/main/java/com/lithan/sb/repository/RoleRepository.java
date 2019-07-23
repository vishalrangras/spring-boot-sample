package com.lithan.sb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lithan.sb.model.Role;

public interface RoleRepository extends JpaRepository<Role, String>{
	
}
