package com.lead.Entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import com.lead.DTO.BillingInfo;
import com.lead.DTO.Tier;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Account_Table")
public class Account {

	@Id
	private String accountId;

	private String name;
	private String domain;
	private String industry;

	@Enumerated(EnumType.STRING)
	private Tier tier;

	@Embedded
	private BillingInfo billingInfo;

	@CreatedBy
	private LocalDateTime createdAt;
	@LastModifiedBy
	private LocalDateTime updatedAt;

}
