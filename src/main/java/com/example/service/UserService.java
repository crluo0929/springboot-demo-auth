package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.DBUser;
import com.example.entity.Role;
import com.example.repo.RoleRepository;
import com.example.repo.UserRepository;
import static com.example.util.IterableUtil.*;

@Service
public class UserService {

	@Autowired UserRepository userDao ;
	@Autowired RoleRepository roleDao ;
	
	public List<DBUser> listUsers(){
		return toList(userDao.findAll());
	}
	
	public DBUser getByName(String name) {
		return userDao.findByName(name).orElse(null);
	}
	
	public DBUser addUser(DBUser user) {
		List<Role> tempRoles = user.getRoles()==null ? new ArrayList<>() : user.getRoles() ;
		user.setRoles(null);
		DBUser resultUser = userDao.save(user);
		int userid = resultUser.getId() ;
		tempRoles.forEach(role -> role.setUserid(userid));
		Iterable<Role> resultRoles = roleDao.saveAll(tempRoles);
		resultUser.setRoles(toList(resultRoles));
		return resultUser ;
	}
	
	public void deleteUserById(int id) {
		userDao.deleteById(id);
	}

}
