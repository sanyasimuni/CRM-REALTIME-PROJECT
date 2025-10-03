package com.lead.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lead.DTO.AccountResponse;
import com.lead.Services.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PostMapping("/add")
	public ResponseEntity<AccountResponse> addAccount(@Valid  @RequestBody AccountResponse accountResponse) {

		AccountResponse accountResponse2 = this.accountService.addAccount(accountResponse);

		return new ResponseEntity<AccountResponse>(accountResponse2, HttpStatus.CREATED);

	}

	@GetMapping("/{id}")
	public ResponseEntity<AccountResponse> getAccountById(@PathVariable("id") String accountId) {
		AccountResponse accountResponse = accountService.SingleAccountById(accountId);
		return ResponseEntity.ok(accountResponse);
	}

	@GetMapping
	public ResponseEntity<List<AccountResponse>> getAllAccounts() {
		List<AccountResponse> accounts = accountService.listofResponse();
		return ResponseEntity.ok(accounts);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable("id") String accountId) {
		accountService.delete(accountId);
		return ResponseEntity.ok("Account deleted successfully");
	}

}
