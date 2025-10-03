package com.lead.Services;

import java.util.List;

import com.lead.DTO.AccountResponse;

public interface AccountService {
	
	public AccountResponse addAccount(AccountResponse accountResponse);
	
	public AccountResponse SingleAccountById(String accountId);
	
	public List<AccountResponse>listofResponse();
	
	void delete(String accountId);
	
	
	
	

}
