package com.lead.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lead.Entity.Account;


@Repository
public interface AccountRepository  extends JpaRepository<Account, String>{

}
