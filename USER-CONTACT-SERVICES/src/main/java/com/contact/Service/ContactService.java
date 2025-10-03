package com.contact.Service;

import java.util.List;

import com.contact.DTO.ContactResponse;
import com.contact.DTO.Status;

public interface ContactService {
	
	
	public ContactResponse createContact(ContactResponse contactResponse);
	
	public ContactResponse updateContact(String contactId,String role,Status status);
	
	public List<ContactResponse>listOfContact();
	
    public ContactResponse singleContact(String contactId);
    public void DeleteContact(String contactId);
    
    public List<ContactResponse>findByAccountId(String accountId);
    
    public List<ContactResponse>findByLeadId(String leadId);

}
