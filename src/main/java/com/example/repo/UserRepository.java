package com.example.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.DBUser;

@Repository
public interface UserRepository extends CrudRepository<DBUser,Integer>{

	public Optional<DBUser> findByName(String name) ;
	
}
