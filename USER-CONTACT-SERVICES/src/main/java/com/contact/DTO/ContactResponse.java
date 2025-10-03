package com.contact.DTO;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import com.contact.Utils.AccountResponse;
import com.contact.Utils.LeadResponse;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactResponse implements Serializable {
	private static final long serialVersionUID = 4135405676247487412L;

	private String contactId;

	@NotBlank(message = "Lead ID cannot be blank")
	private String leadId;

	@NotBlank(message = "Account ID cannot be blank")
	private String accountId;

	@NotBlank(message = "First name is required")
	private String firstName;

	@NotBlank(message = "Last name is required")
	private String lastName;

	@NotEmpty(message = "At least one email is required")
	private List<@Email(message = "Invalid email format") String> emails;

	@NotEmpty(message = "At least one phone number is required")
	private List<@Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits") String> phones;

	private String title;

	@NotBlank(message = "Role is required")
	private String role;

	private Instant createdAt;
	private Instant updatedAt;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private LeadResponse leadResponses;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private AccountResponse  accountResponses;

	// getters/setters
}
