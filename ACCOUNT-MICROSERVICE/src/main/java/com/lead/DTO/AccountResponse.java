package com.lead.DTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class AccountResponse {

	private String accountId;
	@NotBlank(message = "Name is required")
	@Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
	private String name;

	@NotBlank(message = "Domain is required")
	private String domain;

	@NotBlank(message = "Industry is required")
	private String industry;

	@NotNull(message = "Tier is required")
	private Tier tier;

	@NotNull(message = "Billing info is required")
	private BillingInfo billingInfo;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
