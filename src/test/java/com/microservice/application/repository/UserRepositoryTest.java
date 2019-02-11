package com.microservice.application.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.microservice.application.model.User;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest 
{	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Test
	public void testGetAllUsers()
	{	
		List<User> userList = new ArrayList<>();
		userList = userRepository.findAll();
		assertThat(userRepository.findAll()).isEqualTo(userList);
	}	
	
	
	@Test
	public void testSaveUser()
	{		
		User mockUser = new User();
		mockUser.setFirstName("Josh");
		mockUser.setLastName("Brolin");
		mockUser.setDateOfBirth("2/12/1968");
		mockUser.setStatus("ACTIVE");
		
		User userSavedInDB = testEntityManager.persist(mockUser);
		User userFromDB = userRepository.getOne(userSavedInDB.getId());
		
		assertThat(userFromDB).isEqualTo(userSavedInDB);
	}
	
	
	@Test
	public void testUpdateUser()
	{		
		User mockUser = new User();
		mockUser.setFirstName("Thom");
		mockUser.setLastName("Holland");
		mockUser.setDateOfBirth("6/1/1996");
		mockUser.setStatus("ACTIVE");
		
		User userSavedInDB = testEntityManager.persist(mockUser);
		User userFromDB = userRepository.getOne(userSavedInDB.getId());
		
		userFromDB.setFirstName("Thomas");
		userSavedInDB = testEntityManager.persist(userFromDB);
		userFromDB = userRepository.getOne(userSavedInDB.getId());
		
		assertThat(userFromDB.getFirstName()).isEqualTo("Thomas");
	}	
	
	
	@Test
	public void testDeleteUser()
	{		
		User mockUser = new User();
		mockUser.setFirstName("Chris");
		mockUser.setLastName("Pratt");
		mockUser.setDateOfBirth("6/21/1979");
		mockUser.setStatus("ACTIVE");
		
		User userSavedInDB = testEntityManager.persist(mockUser);
		User userFromDB = userRepository.getOne(userSavedInDB.getId());	
		
		userFromDB.setStatus("INACTIVE");;
		userSavedInDB = testEntityManager.persist(userFromDB);
		userFromDB = userRepository.getOne(userSavedInDB.getId());
		
		assertThat(userFromDB.getStatus()).isEqualTo("INACTIVE");
	}	
	
}
