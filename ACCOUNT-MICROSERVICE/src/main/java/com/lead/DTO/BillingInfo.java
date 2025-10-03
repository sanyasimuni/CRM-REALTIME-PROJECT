package com.lead.DTO;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingInfo {
	
	private String address;
    private String city;
    private String country;
    private String taxId;
    private String paymentMethod;


}
