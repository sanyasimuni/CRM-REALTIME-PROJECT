package com.lead.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.DTO.AccountResponse;
import com.lead.DTO.Tier;
import com.lead.Entity.Account;
import com.lead.Exception.AccountIdNotFoundException;
import com.lead.Repo.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository repository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public AccountResponse addAccount(AccountResponse accountResponse) {

		Account account = this.mapper.map(accountResponse, Account.class);
		account.setAccountId(UUID.randomUUID().toString());
		account.setCreatedAt(LocalDateTime.now());
		account.setUpdatedAt(LocalDateTime.now());
		account.setTier(Tier.FREE);

		Account saved = this.repository.save(account);

		return this.mapper.map(saved, AccountResponse.class);
	}

	@Override
	public AccountResponse SingleAccountById(String accountId) {

		Account account = this.repository.findById(accountId)
				.orElseThrow(() -> new AccountIdNotFoundException("ID NOT FOUND EXCEPTION!"));
		return this.mapper.map(account, AccountResponse.class);
	}

	@Override
	public List<AccountResponse> listofResponse() {

		List<Account> account = this.repository.findAll();
		List<AccountResponse> resp = account.stream().map((err) -> this.mapper.map(err, AccountResponse.class))
				.collect(Collectors.toList());

		return resp;
	}

	@Override
	public void delete(String accountId) {

		Account account = this.repository.findById(accountId)
				.orElseThrow(() -> new AccountIdNotFoundException("ID NOT FOUND EXCEPTION!"));

		this.repository.delete(account);

	}

	// ------------------------------------

	public Account acctoDto(AccountResponse response) {

		return this.mapper.map(response, Account.class);

	}

	public AccountResponse DtoToResponse(AccountResponse response) {

		return this.mapper.map(response, AccountResponse.class);

	}

}
