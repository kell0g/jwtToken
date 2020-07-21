package io.kello.ajwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import io.kello.ajwt.domain.account.Account;


public interface AccountRepository extends JpaRepository<Account, Long>{
	Account findByUsername(String username);
}