package com.example.springbootdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootdemo.Entity.User;
import com.example.springbootdemo.Exception.ResourceNotFoundException;
import com.example.springbootdemo.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserRepository userRespository;
	
	@GetMapping
	public List<User> getUsers(){
		return userRespository.findAll();
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable (value="id") long id){
		return userRespository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("User not Found"));
	}
	
	@PostMapping
	public User createUser(@RequestBody User user){
		return userRespository.save(user);
	}
	
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user,@PathVariable (value="id") long id){
		 User existing=userRespository.findById(id)
			.orElseThrow(()-> new ResourceNotFoundException("User not Found"));
		 existing.setFirstName(user.getFirstName());
		 existing.setLastName(existing.getLastName());
		 existing.setEmail(existing.getEmail());
		return this.userRespository.save(existing);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable (value="id") long id){
		User existing=userRespository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("User not Found"));
		this.userRespository.delete(existing);
		return ResponseEntity.ok().build();
	}
}
