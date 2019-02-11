package com.microservice.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "user")
public class User 
{
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_Name")
	private String lastName;
	
	@Column(name="date_of_birth")
	private String dateOfBirth;
	
	@Column(name="status")
	private String status;
		
	public User(){ 
		
	}
	
	public User(String firstName, String lastName, String dateOfBirth, String status){
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.status = status;
	}	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(" [User]");
		sb.append(" {").append("");
		sb.append(" id: ").append(id);
		sb.append(" firstName: ").append(firstName);
		sb.append(" lastName: ").append(lastName);
		sb.append(" dateOfBirth: ").append(dateOfBirth);
		sb.append(" status: ").append(status);
		sb.append(" }");
		
		return sb.toString();
	}
	
}
