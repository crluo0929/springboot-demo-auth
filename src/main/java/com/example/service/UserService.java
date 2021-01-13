package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.DBUser;
import com.example.repo.UserRepository;
import static com.example.util.IterableUtil.*;

@Service
public class UserService {

	@Autowired UserRepository userDao ;
	
	public List<DBUser> listUsers(){
		return toList(userDao.findAll());
	}
	
	public DBUser getByName(String name) {
		return userDao.findByName(name).orElse(null);
	}
	
	public DBUser addUser(DBUser user) {
		return userDao.save(user);
	}
	
	public void deleteUserById(int id) {
		userDao.deleteById(id);
	}

}
