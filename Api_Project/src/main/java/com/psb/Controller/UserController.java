package com.psb.Controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.psb.Dto.UserValue;
import com.psb.Entity.User;
import com.psb.Service.UserService;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RequestMapping(value = "/users", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
public class UserController {
	
	private final UserService userService;
	
	
	@GetMapping("/{id}")
	public Map<String, Object> findById(@PathVariable("id") User user){
		Map<String, Object> result = new HashMap<>();
			
		if(user != null) {
			result.put("key", "success");
			result.put("value", user);
		}else {
			result.put("key", "fail");
			result.put("value", "일치하는 회원 정보가 없습니다. 사용자 id를 확인해주세요.");
		}
		return result;
	}
	
	@PostMapping("")
	public Map<String, Object> save(@RequestBody UserValue value) {
		Map<String, Object> response = new HashMap<>();

		User user = userService.save(value);
		if(user != null) {
			response.put("result", "SUCCESS");
			response.put("user", user);
		} else {
			response.put("result", "FAIL");
			response.put("reason", "회원 가입 실패");
		}

		return response;
	}
	
	@PutMapping("/{id}")
	public Map<String, Object> update(@PathVariable("id") long id, @RequestBody UserValue value) {
		Map<String, Object> response = new HashMap<>();

		if(userService.update(id, value) > 0) {
			response.put("result", "SUCCESS");
		} else {
			response.put("result", "FAIL");
			response.put("reason", "일치하는 회원 정보가 없습니다. 사용자 id를 확인해주세요.");
		}

		return response;
	}
	
	@DeleteMapping("/{id}")
	public Map<String, Object> delete(@PathVariable("id") long id) {
		Map<String, Object> response = new HashMap<>();

		if(userService.delete(id) > 0) {
			response.put("result", "SUCCESS");
		} else {
			response.put("result", "FAIL");
			response.put("reason", "일치하는 회원 정보가 없습니다. 사용자 id를 확인해주세요.");
		}

		return response;
	}





}
