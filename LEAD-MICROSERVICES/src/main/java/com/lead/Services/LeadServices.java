package com.lead.Services;

import java.util.List;

import com.lead.DTO.LeadResponse;
import com.lead.DTO.LeadStatus;


public interface LeadServices {
	
	
	public LeadResponse createLead(LeadResponse leadResponse);

	public LeadResponse UpdateLead(LeadResponse leadResponse, String leadId);

	public List<LeadResponse> listofLead();

	public LeadResponse singleLead(String leadId);

	void delete(String leadId);
	
	public List<LeadResponse> status(LeadStatus status);
	
	
	
	

}
