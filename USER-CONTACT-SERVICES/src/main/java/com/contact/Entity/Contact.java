package com.contact.Entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import com.contact.DTO.Status;
import com.contact.Utils.AccountResponse;
import com.contact.Utils.LeadResponse;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Contact-Table")
@Builder
public class Contact implements Serializable{
	
	private static final long serialVersionUID = 6627497625944462084L;

	@Id
	private String contactId;

	private String leadId; 
	private String accountId; 

	private String firstName;
	private String lastName;

	@Column(name = "email", unique = true)
	private List<String> emails;

	@Column(name = "phone")
	private List<String> phones;

	private String title;
	private String role;
	
	@Enumerated(EnumType.STRING)
	private Status status;

	private Instant createdAt;
	private Instant updatedAt;
	@Transient
	private LeadResponse leadResponses;

	@Transient
	private AccountResponse accountResponses;


}
