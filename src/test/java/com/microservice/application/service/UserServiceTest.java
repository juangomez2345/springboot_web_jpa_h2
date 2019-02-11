package com.microservice.application.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.microservice.application.SpringbootWebJpaH2Application;
import com.microservice.application.model.User;
import com.microservice.application.repository.IUserRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, 
				classes = SpringbootWebJpaH2Application.class)
@AutoConfigureMockMvc
public class UserServiceTest 
{	
	@MockBean
	private IUserRepository userRepository;
	
	@MockBean
	private UserService userService;
	
	@Test
	public void testGetAllUsers() throws Exception 
	{
		User mockUser1 = new User();
		mockUser1.setId(1);
		mockUser1.setFirstName("Josh");
		mockUser1.setLastName("Brolin");
		mockUser1.setDateOfBirth("2/12/1968");
		mockUser1.setStatus("ACTIVE");
		
		User mockUser2 = new User();
		mockUser2.setId(2);
		mockUser2.setFirstName("Chris");
		mockUser2.setLastName("Evans");
		mockUser2.setDateOfBirth("6/13/1981");
		mockUser2.setStatus("ACTIVE");
		
		List<User> userList = new ArrayList<>();
		userList.add(mockUser1);
		userList.add(mockUser2);
		
		Mockito.when(userService.getAllUsers()).thenReturn(userList);		
		assertThat(userService.getAllUsers()).isEqualTo(userList);
	}
	
	
	@Test
	public void testGetUserById() throws Exception 
	{
		User mockUser = new User();
		mockUser.setId(1);
		mockUser.setFirstName("Josh");
		mockUser.setLastName("Brolin");
		mockUser.setDateOfBirth("2/12/1968");
		mockUser.setStatus("ACTIVE");
		
		Mockito.when(userService.getUserById(1)).thenReturn(mockUser);		
		assertThat(userService.getUserById(1)).isEqualTo(mockUser);
	}
	
	
	@Test
	public void testSaveUser() throws Exception 
	{
		User mockUser = new User();
		mockUser.setId(1);
		mockUser.setFirstName("Josh");
		mockUser.setLastName("Brolin");
		mockUser.setDateOfBirth("2/12/1968");
		mockUser.setStatus("ACTIVE");
		
		Mockito.when(userService.saveUser(mockUser)).thenReturn(mockUser);		
		assertThat(userService.saveUser(mockUser)).isEqualTo(mockUser);
	}
	
	
	@Test
	public void testUpdateUser() throws Exception 
	{		
		User mockUser1 = new User();
		mockUser1.setId(1);
		mockUser1.setFirstName("Josh");
		mockUser1.setLastName("Brolin");
		mockUser1.setDateOfBirth("2/12/1968");
		mockUser1.setStatus("ACTIVE");
		
		User mockUser2 = new User();
		mockUser2.setId(1);
		mockUser2.setFirstName("Joshh");
		mockUser2.setLastName("Brolinn");
		mockUser2.setDateOfBirth("2/12/1969");
		mockUser2.setStatus("INACTIVE");
		
		Mockito.when(userService.updateUser(1,mockUser1)).thenReturn(mockUser2);		
		assertThat(userService.updateUser(1,mockUser1)).isEqualTo(mockUser2);
	}
	
	
	@Test
	public void testDeleteUser() throws Exception 
	{
		User mockUser1 = new User();
		mockUser1.setId(1);
		mockUser1.setFirstName("Josh");
		mockUser1.setLastName("Brolin");
		mockUser1.setDateOfBirth("2/12/1968");
		mockUser1.setStatus("ACTIVE");
		
		User mockUser2 = new User();
		mockUser2.setId(1);
		mockUser2.setFirstName("Josh");
		mockUser2.setLastName("Brolin");
		mockUser2.setDateOfBirth("2/12/1968");
		mockUser2.setStatus("INACTIVE");
		
		Mockito.when(userService.deleteUser(1)).thenReturn(mockUser2);		
		assertThat(userService.deleteUser(1)).isEqualTo(mockUser2);
	}
	
}
