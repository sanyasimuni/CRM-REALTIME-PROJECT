package com.lead.Services;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.DTO.LeadResponse;
import com.lead.DTO.LeadStatus;
import com.lead.Entity.Lead;
import com.lead.Exception.LeadIdNotFoundException;
import com.lead.Repo.LeadRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LeadServicesImpl implements LeadServices {

	@Autowired
	private LeadRepository repository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public LeadResponse createLead(LeadResponse leadResponse) {

		Lead lead = this.mapper.map(leadResponse, Lead.class);
		lead.setLeadId(UUID.randomUUID().toString());
		lead.setCreatedAt(Instant.now());
		lead.setUpdatedAt(Instant.now());
		lead.setAssignedTo(UUID.randomUUID().toString());
		Lead lead2 = this.repository.save(lead);
		
		log.info("LEAD SAVE SUCCESS !"+lead2);
		return this.mapper.map(lead2, LeadResponse.class);
	}

	@Override
	public LeadResponse UpdateLead(LeadResponse leadResponse, String leadId) {

		Lead lead = this.repository.findById(leadId)
				.orElseThrow(() -> new LeadIdNotFoundException("LEAD ID NOT FOUND EXCEPTION :"));

		lead.setAssignedTo(leadResponse.getAssignedTo());
		lead.setEmail(leadResponse.getEmail());
		lead.setName(leadResponse.getName());
		lead.setPhone(leadResponse.getPhone());
		lead.setScore(leadResponse.getScore());
		lead.setSource(leadResponse.getSource());
		lead.setStatus(leadResponse.getStatus());

		Lead lead2 = this.repository.save(lead);
		log.info("LEAD UPDATE SUCCESS !"+lead2);
		return this.mapper.map(lead2, LeadResponse.class);
	}

	@Override
	public List<LeadResponse> listofLead() {

		List<Lead> lead = this.repository.findAll();

		List<LeadResponse> resp = lead.stream().map((err) -> this.mapper.map(err, LeadResponse.class))
				.collect(Collectors.toList());
		log.info("LIST OF LEAD ARE FIND !"+resp);

		return resp;
	}

	@Override
	public LeadResponse singleLead(String leadId) {

		Lead lead = this.repository.findById(leadId)
				.orElseThrow(() -> new LeadIdNotFoundException("LEAD ID NOT FOUND EXCEPTION :"));

		log.info("FIND THE SINGLE LEAD !"+lead);
		return this.mapper.map(lead, LeadResponse.class);
	}

	@Override
	public void delete(String leadId) {
		Lead lead = this.repository.findById(leadId)
				.orElseThrow(() -> new LeadIdNotFoundException("LEAD ID NOT FOUND EXCEPTION :"));
		log.info("DELETE THE LEAD ARE SUCCESS !"+lead);
		this.repository.delete(lead);

	}

	@Override
	public List<LeadResponse> status(LeadStatus status) {
		return null;
	}

	public Lead leadToDto(LeadResponse leadResponse) {

		return this.mapper.map(leadResponse, Lead.class);

	}

	public LeadResponse DtoToResponse(Lead leadResponse) {

		return this.mapper.map(leadResponse, LeadResponse.class);

	}

}
