package com.lithan.sb.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lithan.sb.constants.GlobalConstants;
import com.lithan.sb.dto.RegistrationFormDto;
import com.lithan.sb.exceptions.IncorrectDateFormatException;
import com.lithan.sb.exceptions.RoleNotFoundException;
import com.lithan.sb.exceptions.UserNameAlreadyInUseException;
import com.lithan.sb.model.Address;
import com.lithan.sb.model.Role;
import com.lithan.sb.model.User;
import com.lithan.sb.repository.AddressRepository;
import com.lithan.sb.repository.RoleRepository;
import com.lithan.sb.repository.UserRepository;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat(GlobalConstants.DD_MM_YYYY);
	
	public User registerUser(RegistrationFormDto registrationFormDto) throws RoleNotFoundException{
		
		Optional<Role> roleOptional = roleRepository.findById(registrationFormDto.getRoleCode());
		
		/** To make sure that role is present in DB **/
		if(!roleOptional.isPresent())
			throw new RoleNotFoundException(registrationFormDto.getRoleCode());
		
		/** To make sure that username is not already been used by another user **/
		Optional<User> userOptional = userRepository.findById(registrationFormDto.getUserName());
		if(userOptional.isPresent()) 
			throw new UserNameAlreadyInUseException(registrationFormDto.getUserName());
		
		User user = new User();
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
		
		userRepository.save(user);
		
		Address address = null;
		Optional<Address> addressOptional = addressRepository.findById(registrationFormDto.getAddressId());
		if(addressOptional.isPresent()) { 
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
	
}
