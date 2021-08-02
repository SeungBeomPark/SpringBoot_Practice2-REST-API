package com.psb.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.psb.Entity.User;
import com.psb.Service.UserService;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RequestMapping(value = "/api/users")
@RestController
public class UserController {
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/{id}")
	public Map<String, Object> findById(@PathVariable("id") long id){
		Map<String, Object> result = new HashMap<>();
		
		Optional<User> user = userService.findById(id);
		if(user.isPresent()) {
			result.put("key", "success");
			result.put("value", user.get());
		}else {
			result.put("key", "fail");
			result.put("value", "사용자 정보를 찾을 수 없습니다.");
		}
		return result;
	}

}
