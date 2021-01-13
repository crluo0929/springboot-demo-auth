package com.example.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role,Integer>{

	public Iterable<Role> findByUserid(int userid) ;
	
}
