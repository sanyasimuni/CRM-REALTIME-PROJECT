package com.contact.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contact.DTO.Status;
import com.contact.Entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {

	Optional<Contact> findByContactIdAndRoleAndStatus(String contactId, String role, Status status);

	// find contacts by accountId
	List<Contact> findByAccountId(String accountId);

	// find contacts by leadId
	List<Contact> findByLeadId(String leadId);

}
