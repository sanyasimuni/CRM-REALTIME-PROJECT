package com.contact.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.contact.DTO.ContactResponse;
import com.contact.DTO.Status;
import com.contact.Entity.Contact;
import com.contact.Exception.ContactNotFoundException;
import com.contact.Exception.ResourceNotFoundException;
import com.contact.Repo.ContactRepository;
import com.contact.Utils.AccountResponse;
import com.contact.Utils.LeadResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository repository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ContactResponse createContact(ContactResponse contactResponse) {

		Contact contact = this.mapper.map(contactResponse, Contact.class);

		contact.setContactId(UUID.randomUUID().toString());
		contact.setCreatedAt(Instant.now());
		contact.setUpdatedAt(Instant.now());

		Contact saved = this.repository.save(contact);
		return this.mapper.map(saved, ContactResponse.class);
	}

	

	@Override
	public List<ContactResponse> listOfContact() {

		List<Contact> contact = this.repository.findAll();
		List<ContactResponse> resp = contact.stream().map((er) -> this.mapper.map(er, ContactResponse.class))
				.collect(Collectors.toList());
		

		return resp;
	}

	@Cacheable(key ="#contactId",value = "Contact")
	@Override
	public ContactResponse singleContact(String contactId) {

		Contact contact = this.repository.findById(contactId)
	            .orElseThrow(() -> new ContactNotFoundException("CONTACT NOT FOUND EXCEPTION"));

	    // ✅ Correct URL (one ID only)
	    LeadResponse leadResponse = restTemplate.getForObject(
	            "http://LEAD-MICROSERVICE/api/leads/" + contact.getLeadId(),
	            LeadResponse.class
	    );
	  
	    AccountResponse accountResponse = restTemplate.getForObject(
	            "http://ACCOUNT-MICROSERVICE/api/accounts/" + contact.getAccountId(),
	            AccountResponse.class
	    );
	  
	 // Map your JPA entity to the DTO
	    ContactResponse contactResponse = this.mapper.map(contact, ContactResponse.class);

	    // Attach the external microservice data
	    contactResponse.setLeadResponses(leadResponse);
	    contactResponse.setAccountResponses(accountResponse);

	    // Return the DTO
	    return contactResponse;

	}

	
	
	
	@CacheEvict(value = "Contact",key = "#contactId")
	@Override
	public void DeleteContact(String contactId) {

		Contact contact = this.repository.findById(contactId)
				.orElseThrow(() -> new ContactNotFoundException("CONTACT NOT FOUND EXCEPTION"));

		this.repository.delete(contact);

	}

	public Contact contcToDto(ContactResponse contactResponse) {
		return this.mapper.map(contactResponse, Contact.class);
	}

	public ContactResponse DtoToContact(Contact contactResponse) {
		return this.mapper.map(contactResponse, ContactResponse.class);
	}

	
	@CachePut(key = "Contact.contactId",value = "Contact")
	@Override
	public ContactResponse updateContact(String contactId, String role, Status status) {

		if (contactId == null || contactId.trim().isEmpty()) {
			throw new IllegalArgumentException("Contact ID must not be null or empty");
		}
		Optional<Contact> contactOpt = repository.findByContactIdAndRoleAndStatus(contactId, role, status);

		Contact contact = contactOpt.orElseThrow(
				() -> new ResourceNotFoundException("No contact found with id " + contactId + " for role " + role));

		Contact updated = repository.save(contact);
		return this.mapper.map(updated, ContactResponse.class);
	}

	
	
	@Override
	public List<ContactResponse> findByAccountId(String accountId) {

		List<Contact> contact = this.repository.findByAccountId(accountId);
		List<ContactResponse> resp = contact.stream().map((er) -> this.mapper.map(er, ContactResponse.class))
				.collect(Collectors.toList());

		return resp;
	}

	@Override
	public List<ContactResponse> findByLeadId(String leadId) {

		List<Contact> contact = this.repository.findByLeadId(leadId);
		List<ContactResponse> resp = contact.stream().map((er) -> this.mapper.map(er, ContactResponse.class))
				.collect(Collectors.toList());

		return resp;
	}

}
