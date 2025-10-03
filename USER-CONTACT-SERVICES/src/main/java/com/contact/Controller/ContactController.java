package com.contact.Controller;

import java.time.Instant;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.contact.DTO.ContactResponse;
import com.contact.DTO.Status;
import com.contact.Service.ContactService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/contact")
public class ContactController {

	@Autowired
	private ContactService contactService;

	@PostMapping("/add")
	public ResponseEntity<ContactResponse> createContact(@Valid @RequestBody ContactResponse contactResponse) {
		ContactResponse created = contactService.createContact(contactResponse);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@GetMapping
	public ResponseEntity<List<ContactResponse>> listOfContact() {
		List<ContactResponse> contacts = contactService.listOfContact();
		return ResponseEntity.ok(contacts);
	}

	
	
	@CircuitBreaker(name = "User-Contact-Service",fallbackMethod = "ContactServiceFallBackMethod")
	@GetMapping("/{id}")
	public ResponseEntity<ContactResponse> singleContact(@PathVariable("id") String contactId) {
		ContactResponse contact = contactService.singleContact(contactId);
		return ResponseEntity.ok(contact);
	}
	
	
	public ResponseEntity<ContactResponse>ContactServiceFallBackMethod(String contactId,Exception ex){
		
		log.info("User-service is down after some time ....."+ex.getMessage());
		
		ContactResponse dummyLead = ContactResponse.builder()
		        .leadId("L123")
		        .accountId("A456")
		        .firstName("John")
		        .lastName("Doe")
		        .emails(List.of("john.doe@example.com", "j.doe@workmail.com"))
		        .phones(List.of("+1234567890", "+0987654321"))
		        .title("Sales Manager")
		        .role("Decision Maker")
		          // assuming you have an enum called Status with value ACTIVE
		        .createdAt(Instant.now())
		        .updatedAt(Instant.now())
		        .build();
		
		
		
		return new ResponseEntity<>(dummyLead,HttpStatus.OK);
		
	}
	
	
	

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteContact(@PathVariable("id") String contactId) {
		contactService.DeleteContact(contactId);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<ContactResponse> updateContact(@PathVariable("id") String contactId,
			@RequestParam String role, @RequestParam Status status) {
		ContactResponse updated = contactService.updateContact(contactId, role, status);
		return ResponseEntity.ok(updated);
	}

	@GetMapping("/account/{accountId}")
	public ResponseEntity<List<ContactResponse>> getContactsByAccountId(@PathVariable String accountId) {
		List<ContactResponse> contacts = contactService.findByAccountId(accountId);
		return ResponseEntity.ok(contacts);
	}

	@GetMapping("/lead/{leadId}")
	public ResponseEntity<List<ContactResponse>> getContactsByLeadId(@PathVariable String leadId) {
		List<ContactResponse> contacts = contactService.findByLeadId(leadId);
		return ResponseEntity.ok(contacts);
	}
}
