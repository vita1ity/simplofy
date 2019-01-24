package org.crama.simplofy.repository;

import java.util.List;

import org.crama.simplofy.model.Account;
import org.crama.simplofy.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {

	public List<Story> findByAccount(Account account);
	
}
