package org.crama.simplofy.repository;

import java.util.List;

import org.crama.simplofy.model.Chapter;
import org.crama.simplofy.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends JpaRepository<Page, Long>{

	Page findByChapterAndPageNumber(Chapter chapter, int pageNumber);

	List<Page> findByChapter(Chapter chapter);

	List<Page> findByChapterAndPageNumberGreaterThan(Chapter chapter, int pageNumber);

	
	
}
