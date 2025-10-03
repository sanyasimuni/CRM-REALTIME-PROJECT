package com.lead.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lead.DTO.LeadResponse;
import com.lead.Services.LeadServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

	@Autowired
	private LeadServices leadServices;

	@PostMapping("/lead")
	public ResponseEntity<LeadResponse> createLead(@Valid  @RequestBody LeadResponse leadResponse) {
		LeadResponse leadResponse2 = this.leadServices.createLead(leadResponse);
		return new ResponseEntity<>(leadResponse2, HttpStatus.CREATED);

	}

	@PutMapping("/{leadId}")
	public ResponseEntity<LeadResponse> updateLead(@PathVariable String leadId,
			@RequestBody LeadResponse leadResponse) {

		LeadResponse updatedLead = this.leadServices.UpdateLead(leadResponse, leadId);
		return ResponseEntity.ok(updatedLead);
	}

	@GetMapping
	public ResponseEntity<List<LeadResponse>> getAllLeads() {
		List<LeadResponse> leads = this.leadServices.listofLead();
		return ResponseEntity.ok(leads);
	}

	@DeleteMapping("/{leadId}")
	public ResponseEntity<String> deleteLead(@PathVariable String leadId) {
		leadServices.delete(leadId);
		return ResponseEntity.ok("Lead with ID " + leadId + " deleted successfully");
	}

	@GetMapping("/{leadId}")
	public ResponseEntity<LeadResponse> getLeadById(@PathVariable String leadId) {
		LeadResponse lead = leadServices.singleLead(leadId);
		return ResponseEntity.ok(lead);
	}

}
