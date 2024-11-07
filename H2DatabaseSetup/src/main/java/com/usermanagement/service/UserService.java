package com.usermanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usermanagement.model.UserEntity;
import com.usermanagement.model.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	public List<UserEntity> getAllUsers(){
		System.out.println("getAllUsers");
		
		List<UserEntity> users = new ArrayList<UserEntity>();  
		repository.findAll().forEach(user -> users.add(user));  
		System.out.println("user add" + users);
		return users;  
	}
	
	public List<UserEntity> getByKeyword(String keyword){
		  return repository.findByKeyword(keyword);
		 }
	
	public List<UserEntity> getByAge(Number minage, Number maxage){
		  return repository.findByAge(minage, maxage);
		 }
	
	public UserEntity getUserById(Long id) throws Exception {
		System.out.println("getUserById");
		Optional<UserEntity> user = repository.findById(id);
		
		if(user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}
	
	public UserEntity createOrUpdateUser(UserEntity entity) {
		System.out.println("createOrUpdateUser " + entity);
		if(entity.getId() == null) {
			entity = repository.save(entity);
			return entity;
		} else {
			Optional<UserEntity> user = repository.findById(entity.getId());
			if(user.isPresent()) {
				UserEntity newEntity = user.get();
				newEntity.setFirstname(entity.getFirstname());
				newEntity.setSecondname(entity.getSecondname());
				newEntity.setAddress(entity.getAddress());
				
					newEntity.setDateofbirth(entity.getDateofbirth());
				
				newEntity.setPhone(entity.getPhone());
				newEntity.setPhoto(entity.getPhoto());
				
				newEntity = repository.save(newEntity);
				return newEntity;
			} else { 
				entity = repository.save(entity);
				return entity;
			}
		}
	}
	
	public void deleteUserById(Long id) throws Exception {
		System.out.println("deleteUserById");
		Optional<UserEntity> user = repository.findById(id);
		if(user.isPresent()) {
			repository.deleteById(id);
		} else { 
			
		}
	}

}
