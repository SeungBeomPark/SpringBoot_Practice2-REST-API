package com.psb.Service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.psb.Dto.UserValue;
import com.psb.Entity.User;
import com.psb.Repository.UserRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	@Transactional(readOnly  = true)
	public Optional<User> findById(Long id) {
	    return userRepository.findById(id);
	  }
	
	@Transactional
	public User save(UserValue value) {
		User user = new User(value.getType(), value.getEmail(), value.getBirthDate(),           
				value.getName(), value.getPassword(), value.getPhoneNumber(), value.getSex());
		return userRepository.save(user);

	}
	
	@Transactional
	public int update(long id, UserValue value) {
		Optional<User> oUser = userRepository.findById(id);
		if(oUser.isPresent()) {
			User user = oUser.get();
			if(!value.getType().isEmpty()) {
				user.setType(value.getType());
			}
			if(!value.getEmail().isEmpty()) {
				user.setEmail(value.getEmail());
			}
			if(!value.getBirthDate().isEmpty()) {
				user.setBirthDate(value.getBirthDate());
			}
			if(!value.getName().isEmpty()) {
				user.setName(value.getName());
			}
			if(!value.getPassword().isEmpty()) {
				user.setPassword(value.getPassword());
			}
			if(!value.getPhoneNumber().isEmpty()) {
				user.setPhoneNumber(value.getPhoneNumber());
			}
			if(!value.getSex().isEmpty()) {
				user.setSex(value.getSex());
			}
			userRepository.save(user);
			return 1;
		}
		return 0;
	}
	
	@Transactional
	public int delete(long id) {
		Optional<User> oUser = userRepository.findById(id);
		if(oUser.isPresent()) {
			userRepository.delete(oUser.get());
			return 1;
		}
		return 0;
	}

}
