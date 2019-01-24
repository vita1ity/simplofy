package org.crama.simplofy.repository;

import org.crama.simplofy.model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long>{

	
	
}
