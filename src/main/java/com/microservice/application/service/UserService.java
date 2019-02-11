package com.microservice.application.service;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microservice.application.model.User;
import com.microservice.application.repository.IUserRepository;


@Service
public class UserService 
{	
	@Autowired
	private IUserRepository userRepository;
	
	public List<User> getAllUsers()
	{
		List<User> finalList = userRepository.findAll();
		finalList.removeIf(user -> user.getStatus().equals("INACTIVE"));
		return finalList;
	}
	
	
	public User getUserById(long id)
	{
		User userToBeLocated = null;
		try
		{
			userToBeLocated = userRepository.getOne(id);			
			
			if(userToBeLocated!=null)
			{
				return userToBeLocated;
			}
		}
		catch(EntityNotFoundException e)
		{
			System.err.print("UserService.getUserById::unable to find user");;
		}
		
		return userToBeLocated;
	}
	
	
	public User saveUser(User user)
	{
		
		if(!user.getFirstName().equals("") && !user.getLastName().equals("") && !user.getDateOfBirth().equals(""))
		{
			user.setStatus("ACTIVE");
			return userRepository.save(user);
		}
		return new User();
	}
	
	
	public User updateUser(long id, User user)
	{
		User userToBeUpdated = null;
		try
		{
			userToBeUpdated = userRepository.getOne(id);
			
			if(userToBeUpdated!=null)
			{
				userToBeUpdated.setFirstName(user.getFirstName());
				userToBeUpdated.setLastName(user.getLastName());
				userToBeUpdated.setDateOfBirth(user.getDateOfBirth());
				
				return userRepository.save(userToBeUpdated);
			}
		}
		catch(EntityNotFoundException e)
		{
			System.err.print("UserService.updateUser::unable to update user");;
		}

		return userToBeUpdated;
	}
	
	
	public User deleteUser(long id)
	{
		User userToBeDeleted = null;
		try
		{
			userToBeDeleted = userRepository.getOne(id);
			
			if(userToBeDeleted!=null)
			{
				userToBeDeleted.setStatus("INACTIVE");
				
				return userRepository.save(userToBeDeleted);
			}
		}
		catch(EntityNotFoundException e)
		{
			System.err.print("UserService.deleteUser::unable to delete user");;
		}

		return userToBeDeleted;
	}

}
