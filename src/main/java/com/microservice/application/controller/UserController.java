package com.microservice.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.microservice.application.model.User;
import com.microservice.application.service.UserService;

@RestController
public class UserController 
{	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers()
	{
		return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
	}
	
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) 
    {
		return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
	}
    
    @RequestMapping(value = "/user", method = RequestMethod.POST)
   	public ResponseEntity<User> saveUser(@RequestBody User user) 
    {
		return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.OK);
   	}
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
   	public ResponseEntity<User>  updateUser(@PathVariable("id") long id, @RequestBody User user) 
    {
		return new ResponseEntity<User>(userService.updateUser(id, user), HttpStatus.OK);
   	}

    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id)
    {
		return new ResponseEntity<User>(userService.deleteUser(id), HttpStatus.OK);
	}  
    
}
