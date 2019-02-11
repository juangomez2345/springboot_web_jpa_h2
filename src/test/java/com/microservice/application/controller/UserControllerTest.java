package com.microservice.application.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.application.SpringbootWebJpaH2Application;
import com.microservice.application.model.User;
import com.microservice.application.repository.IUserRepository;
import com.microservice.application.service.UserService;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, 
				classes = SpringbootWebJpaH2Application.class)
@AutoConfigureMockMvc
public class UserControllerTest {
    
	@Autowired
	private MockMvc mockMvc;
	
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
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/user")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String expectedJson = this.mapToJson(userList);
		String outputInJson = response.getContentAsString();
		
		assertThat(outputInJson).isEqualTo(expectedJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
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
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/user/1")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String expectedJson = this.mapToJson(mockUser);	
		String outputInJson = response.getContentAsString();
		
		assertThat(outputInJson).isEqualTo(expectedJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
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
		
		String inputInJson = this.mapToJson(mockUser);
		
		Mockito.when(userService.saveUser(Mockito.any(User.class))).thenReturn(mockUser);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/user")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(inputInJson);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String outputInJson = response.getContentAsString();;
		
		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	
	@Test
	public void testUpdateUser() throws Exception 
	{		
		User mockUser = new User();
		mockUser.setId(1);
		mockUser.setFirstName("Josh");
		mockUser.setLastName("Brolinn");
		mockUser.setDateOfBirth("2/12/1968");
		mockUser.setStatus("ACTIVE");
		
		String inputInJson = this.mapToJson(mockUser);
		
		Mockito.when(userService.updateUser(1,mockUser)).thenReturn(mockUser);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/user/1")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.characterEncoding("UTF-8")
				.content(inputInJson);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertThat(inputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}	
	
	
	@Test
	public void testDeleteUser() throws Exception 
	{
		User mockUser = new User();
		mockUser.setId(1);
		mockUser.setFirstName("Josh");
		mockUser.setLastName("Brolin");
		mockUser.setDateOfBirth("2/12/1968");
		mockUser.setStatus("INACTIVE");
		
		Mockito.when(userService.deleteUser(1)).thenReturn(mockUser);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/user/1")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String expectedJson = this.mapToJson(mockUser);
		String outputInJson = response.getContentAsString();
		
		assertThat(outputInJson).isEqualTo(expectedJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}	
		
	
	private String mapToJson(Object object) throws JsonProcessingException 
	{
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
