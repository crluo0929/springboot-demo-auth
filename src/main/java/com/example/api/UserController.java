package com.example.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.DBUser;
import com.example.service.UserService;

@RestController
public class UserController {

	@Autowired UserService userService ;
	
	@GetMapping("/api/user/list")
	public List<DBUser> listUsers(){
		return userService.listUsers();
	}
	
	@GetMapping("/api/user/name/{name}")
	public DBUser getByName(@PathVariable String name) {
		return userService.getByName(name);
	}
	
	@PutMapping("/api/user")
	public DBUser addUser(@RequestBody DBUser user) {
		return userService.addUser(user);
	}
	
	@DeleteMapping("/api/user/{id}")
	public ResultMessage deleteUserById(@PathVariable int id) {
		try {
			userService.deleteUserById(id) ;
		}catch(Exception ex) {
			return new ResultMessage(500,0,"刪除失敗:"+ex.getMessage()) ;
		}
		return new ResultMessage(200,0,"刪除成功") ;
	}
	
}
