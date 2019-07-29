package com.lithan.sb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import com.lithan.sb.constants.GlobalConstants;
import com.lithan.sb.dto.UserDto;
import com.lithan.sb.model.Address;
import com.lithan.sb.model.Role;
import com.lithan.sb.model.User;
import com.lithan.sb.projection.AddressProjection;
import com.lithan.sb.repository.AddressRepository;
import com.lithan.sb.repository.RoleRepository;
import com.lithan.sb.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Service
public class ApplicationTest {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	public static final SimpleDateFormat sdf = new SimpleDateFormat(GlobalConstants.DD_MM_YYYY);
	
	@Test
	public void testRepositories() throws Exception{
		
		if(roleRepository.count()<=0) {
			
			List<Role> roleList = new ArrayList<>();
			
			Role role = new Role();
			role.setRoleCode("Admin");
			role.setRoleName("Administrator");
			roleList.add(role);
			
			role = new Role();
			role.setRoleCode("User");
			role.setRoleName("Regular User");
			roleList.add(role);
			
			role = new Role();
			role.setRoleCode("Moderator");
			role.setRoleName("Community Moderator");
			roleList.add(role);
			roleRepository.saveAll(roleList);
		}
		
		if(userRepository.count()<=0) {
			
			List<User> userList = new ArrayList<>();
			
			Role role = new Role();
			role.setRoleCode("Admin");
			//role.setRoleName("Administrator");
			
			Address address = new Address();
			address.setActive(true);
			address.setPrimaryAddr(true);
			address.setAddress1("Meryl Street");
			address.setAddress2("Boulevard");
			address.setArea("Pasedena");
			address.setCity("San Francisco");
			address.setState("CA");
			address.setCountry("US");
			address.setContactNo("+1-807-374-9574");
			address.setEmail("daniel_joe@blundermail.com");
			
			
			User user = new User();
			user.setUserName("daniel_joe");
			user.setFirstName("Daniel");
			user.setLastName("Joe");
			user.setDateOfBirth(sdf.parse("02/09/1986"));
			user.setRole(role);
			address.setUser(user);
			user.getAddressList().add(address);
			
			//2nd Address of 1st User
			address = new Address();
			address.setActive(true);
			address.setPrimaryAddr(false);
			address.setAddress1("8375,");
			address.setAddress2("North Golden Star Road ");
			address.setArea("Winter Park");
			address.setCity("Orlando");
			address.setState("FL");
			address.setCountry("US");
			address.setContactNo("+1-708-437-7495");
			address.setEmail("daniel_joe@blundermail.com");
			address.setUser(user);
			user.getAddressList().add(address);
			userList.add(user);
			
			//2nd User
			role = new Role();
			role.setRoleCode("User");
			//role.setRoleName("Regular User");
			
			address = new Address();
			address.setActive(true);
			address.setPrimaryAddr(true);
			address.setAddress1("79");
			address.setAddress2("Joy Ridge St.");
			address.setArea("Spiketurn");
			address.setCity("Melbourne");
			address.setState("GA");
			address.setCountry("US");
			address.setContactNo("+1-274-363-3475");
			address.setEmail("jane_dunphy@thundermail.com");
			
			user = new User();
			user.setUserName("jane_dunphy");
			user.setFirstName("Jane");
			user.setLastName("Dunphy");
			user.setDateOfBirth(sdf.parse("27/02/1974"));
			user.setRole(role);
			address.setUser(user);
			user.getAddressList().add(address);
			userList.add(user);
			
			//3rd User
			role = new Role();
			role.setRoleCode("Moderator");
			//role.setRoleName("Community Moderator");
			
			address = new Address();
			address.setActive(true);
			address.setPrimaryAddr(true);
			address.setAddress1("855");
			address.setAddress2("S. Amergie St.");
			address.setArea("Mystic Grill");
			address.setCity("Jacksonville");
			address.setState("VA");
			address.setCountry("US");
			address.setContactNo("+1-274-363-3475");
			address.setEmail("gloria_pritchet@fakemail.com");
			
			user = new User();
			user.setUserName("gloria_pritchet");
			user.setFirstName("Gloria");
			user.setLastName("Pritchet");
			user.setDateOfBirth(sdf.parse("14/02/1988"));
			user.setRole(role);
			address.setUser(user);
			user.getAddressList().add(address);
			userList.add(user);
			
			userRepository.saveAll(userList);
		}
		
		
		List<User> userList = userRepository.findAll();
		assertNotNull(userList);
			
		List<Role> roleList = roleRepository.findAll();
		assertNotNull(roleList);
		
		List<Address> addressList = addressRepository.findAll();
		assertNotNull(addressList);
		
		UserDto userDto = userRepository.findDtoedByUserName("daniel_joe", UserDto.class);
		assertNotNull(userDto);
		assertEquals("Daniel", userDto.getFirstName());
		assertEquals("Admin", userDto.getRoleRoleCode());
		
		Optional<User> retrievedUserOptional = userRepository.findById("daniel_joe"); 
		assertTrue(retrievedUserOptional.isPresent());
		User retrievedUser = retrievedUserOptional.get();
		assertEquals(2, retrievedUser.getAddressList().size());
		
		List<AddressProjection> addressProjectionList = addressRepository.findAllProjectedByUserUserName("jane_dunphy", AddressProjection.class);
		assertNotNull(addressProjectionList);
		assertEquals(1, addressProjectionList.size());
		AddressProjection addressProjection = addressProjectionList.get(0);
		assertEquals("jane_dunphy@thundermail.com", addressProjection.getEmail());
	}

}
