package com.douchebag.testing;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;


@Entity
@Table(name="contact")
public class Contact {
	private long id; 
	private String firstName;
	private String lastName;
	private String email;

	@Column(name="email")
	public String getEmail() {
		return email;
	}
	
	@Column(name="firstname")
	public String getFirstName() {
		return firstName;
	}
	
	@Column(name="lastname")
	public String getLastName() {
		return lastName;
	}
	
	@Id
	@Column(name="id")
	public long getId() {
		return id;
	}
	
	public void setEmail(String string) {
		email = string;
	}
	
	public void setFirstName(String string) {
		firstName = string;
	}
	
	public void setLastName(String string) {
		lastName = string;
	}
	
	public void setId(long l) {
		id = l;
	}	
}
