package com.lithan.sb.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lithan.sb.constants.GlobalConstants;
import com.lithan.sb.dto.AddressDto;
import com.lithan.sb.dto.RegistrationFormDto;
import com.lithan.sb.dto.UserDto;
import com.lithan.sb.exceptions.IncorrectDateFormatException;
import com.lithan.sb.exceptions.RoleNotFoundException;
import com.lithan.sb.exceptions.UserNameAlreadyInUseException;
import com.lithan.sb.exceptions.UserNotFoundException;
import com.lithan.sb.model.Address;
import com.lithan.sb.model.Role;
import com.lithan.sb.model.User;
import com.lithan.sb.projection.AddressProjection;
import com.lithan.sb.repository.AddressRepository;
import com.lithan.sb.repository.RoleRepository;
import com.lithan.sb.repository.UserRepository;

@Service
@Transactional( rollbackFor = Exception.class)
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat(GlobalConstants.DD_MM_YYYY);
	
	public boolean initializeRoles() {
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
		return true;
	}
	
	public User registerUser(RegistrationFormDto registrationFormDto, boolean update) throws RoleNotFoundException{
		
		Optional<Role> roleOptional = roleRepository.findById(registrationFormDto.getRoleCode());
		
		/** To make sure that role is present in DB **/
		if(!roleOptional.isPresent())
			throw new RoleNotFoundException(registrationFormDto.getRoleCode());
		
		/** To make sure that username is not already been used by another user **/
		Optional<User> userOptional = userRepository.findById(registrationFormDto.getUserName());
		User user = null;
		if(update) {
			if(!userOptional.isPresent())
				throw new UserNotFoundException(registrationFormDto.getUserName());
			user = userOptional.get();
		}else {
			if(userOptional.isPresent()) 
				throw new UserNameAlreadyInUseException(registrationFormDto.getUserName());
			user = new User();
		}
		
		
		user.setFirstName(registrationFormDto.getFirstName());
		user.setLastName(registrationFormDto.getLastName());
		user.setUserName(registrationFormDto.getUserName());
		user.setRole(roleOptional.get());
		try {
			user.setDateOfBirth(sdf.parse(registrationFormDto.getDateOfBirth()));
		}
		catch (ParseException e) {
			throw new IncorrectDateFormatException(GlobalConstants.DD_MM_YYYY, "Date Of Birth");
		}
		
		user = userRepository.save(user);
		
		Address address = null;
		Optional<Address> addressOptional = addressRepository.findById(registrationFormDto.getAddressId());
		if(addressOptional.isPresent() && update) { 
			address = addressOptional.get();
		}else {
			address = new Address();
		}
		address.setActive(registrationFormDto.isActive());
		address.setAddress1(registrationFormDto.getAddress1());
		address.setAddress2(registrationFormDto.getAddress2());
		address.setArea(registrationFormDto.getArea());
		address.setCity(registrationFormDto.getCity());
		address.setContactNo(registrationFormDto.getContactNo());
		address.setCountry(registrationFormDto.getCountry());
		address.setEmail(registrationFormDto.getEmail());
		address.setPrimaryAddr(registrationFormDto.isPrimaryAddr());
		address.setState(registrationFormDto.getState());
		address.setUser(user);
		addressRepository.save(address);
		return user;
	}
	
	public UserDto showUser(String userName) {
		return userRepository.findDtoedByUserName(userName, UserDto.class);
	}
	
	public String saveAddress(AddressDto addressDto) {
		Address address = new Address();
		if(addressDto.getAddressId()>0)
			address.setAddressId(addressDto.getAddressId());
		address.setActive(addressDto.isActive());
		address.setAddress1(addressDto.getAddress1());
		address.setAddress2(addressDto.getAddress2());
		address.setArea(addressDto.getArea());
		address.setCity(addressDto.getCity());
		address.setCountry(addressDto.getCountry());
		address.setContactNo(addressDto.getContactNo());
		address.setEmail(addressDto.getEmail());
		address.setPrimaryAddr(addressDto.isPrimaryAddr());
		address.setState(addressDto.getState());
		User user = new User();
		user.setUserName(addressDto.getUserUserName());
		address.setUser(user);
		return "Address saved successfully for user: "+addressDto.getUserUserName();
	}
	
	public List<UserDto> listUsers(){
		return userRepository.findAllDtoedBy(UserDto.class);
	}
	
	public List<AddressProjection> listAddressesByUser(String userName){
		return addressRepository.findAllProjectedByUserUserName(userName, AddressProjection.class);
	}
	
	public void deleteUser(String userName) {
		userRepository.deleteById(userName);
	}
	
	public void deleteAddress(long addressId) {
		addressRepository.deleteById(addressId);
	}
	
}
