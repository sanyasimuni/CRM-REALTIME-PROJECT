package com.lead.DTO;

import java.time.Instant;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LeadResponse {

	private String leadId; 

	@NotBlank(message = "Name is required")
	private String name;

	@NotBlank(message = "Email is required")
	@Email(message = "Email must be valid")
	private String email;

	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
	private String phone;

	@NotNull(message = "Source is required")
	private LeadSource source;

	@NotNull(message = "Status is required")
	private LeadStatus status;

	private String assignedTo;

	@Min(value = 0, message = "Score must be >= 0")
	@Max(value = 100, message = "Score must be <= 100")
	private int score;

	private Instant createdAt; // usually set automatically
	private Instant updatedAt; // usually set automatically

}
