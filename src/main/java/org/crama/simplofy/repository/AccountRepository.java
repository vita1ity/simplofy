package org.crama.simplofy.repository;

import org.crama.simplofy.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	public Account findByEmail(String email);
	
}
