package com.psb.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.psb.Entity.User;
import com.psb.Repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Transactional(readOnly  = true)
@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	public Optional<User> findById(Long id) {
	    return userRepository.findById(id);
	  }
	
	


}
