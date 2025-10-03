package com.lead.Entity;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.lead.DTO.LeadSource;
import com.lead.DTO.LeadStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "LEAD_TABLE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lead {

	@Id
	private String leadId;

	private String name;
	@Column(unique = true)
	private String email;
	private String phone;

	@Enumerated(EnumType.STRING)
	private LeadSource source;

	@Enumerated(EnumType.STRING)
	private LeadStatus status;

	private String assignedTo; // userId

	private int score;

	@CreatedDate
	private Instant createdAt;

	@LastModifiedDate
	private Instant updatedAt;

}
