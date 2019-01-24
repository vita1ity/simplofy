package org.crama.simplofy.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.crama.simplofy.controller.dto.AddPageDto;
import org.crama.simplofy.controller.dto.PageOverview;
import org.crama.simplofy.model.Chapter;
import org.crama.simplofy.model.Page;
import org.crama.simplofy.model.Story;
import org.crama.simplofy.repository.PageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageService {

	private static final Logger logger = LoggerFactory.getLogger(PageService.class);
	
	@Autowired
	private PageRepository pageRepository;
	
	public Page save(AddPageDto addPageObj, Chapter chapter) {
		
		List<Page> pages = chapter.getPages();
		int pageNumber = pages.size() + 1;
		
		Page page = new Page(addPageObj);
		page.setChapter(chapter);
		page.setPageNumber(pageNumber);
		page.setTitle("Page " + pageNumber);
		
		Page saved = pageRepository.save(page);
		
		return saved;
	}

	public void delete(long id) {
		
		Page page = pageRepository.findOne(id);
		int pageNumber = page.getPageNumber();
		List<Page> pages = pageRepository.findByChapterAndPageNumberGreaterThan(page.getChapter(), pageNumber);
		for (Page p: pages) {
			p.setPageNumber(p.getPageNumber() - 1);
			p.setTitle("Page " + p.getPageNumber());
			pageRepository.save(p);
		}
		
		pageRepository.delete(id);
	}

	public Page findPageById(long id) {
		Page page = pageRepository.findOne(id);
		return page;
	}

	public Page update(AddPageDto addPageObj, Page oldPage) {
		String title = addPageObj.getTitle();
		if (title != null) {
			oldPage.setTitle(addPageObj.getTitle());
		}
		String content = addPageObj.getPageContent(); 
		if (content != null && content != "") {
			oldPage.setPageContent(content);
		}
		
		return pageRepository.save(oldPage);
	}

	public Page getNext(Page page) {
		
		List<Page> allPages = pageRepository.findByChapter(page.getChapter());
		
		Collections.sort(allPages);
		
		logger.info("pageNumber: " + page.getPageNumber());
		logger.info("allPages: " + allPages);
		
		for (Page p: allPages) {
			
			if (p.getPageNumber() > page.getPageNumber()) {
				return p;
			}
			
		}
		
		//no next page - create new one without a title
		Page newPage = new Page();
		newPage.setChapter(page.getChapter());
		newPage.setPageNumber(page.getPageNumber() + 1);
		newPage.setTitle("Page " + newPage.getPageNumber());
		newPage = pageRepository.save(newPage);
		
		return newPage;
	}


	public Page getPrev(Page page) {
		List<Page> allPages = pageRepository.findByChapter(page.getChapter());
		
		Collections.sort(allPages, Collections.reverseOrder());
		
		logger.info("pageNumber: " + page.getPageNumber());
		logger.info("allPages: " + allPages);
		
		for (Page p: allPages) {
			
			if (p.getPageNumber() < page.getPageNumber()) {
				return p;
			}
			
		}
		
		//no previous page - return null
		return null;
	}

	public boolean checkPrevExist(Page page) {
		
		List<Page> allPages = pageRepository.findByChapter(page.getChapter());
		
		Collections.sort(allPages, Collections.reverseOrder());
		
		logger.info("pageNumber: " + page.getPageNumber());
		logger.info("allPages: " + allPages);
		
		for (Page p: allPages) {
			
			if (p.getPageNumber() < page.getPageNumber()) {
				return true;
			}
			
		}
		
		return false;
	}

	/*public Story formOverviewPages(Story story) {
		
		List<Chapter> chapters = story.getChapters();
		for (Chapter chapter: chapters) {
			List<Page> pages = chapter.getPages();
			List<PageOverview> pageOverviewList = new ArrayList<PageOverview>();
 			for (Page page: pages) {
				PageOverview pageOverview = new PageOverview(page);
				pageOverviewList.add(pageOverview);
			}
			chapter.setPageOverviewList(pageOverviewList);
			
		}
		
		return story;
	}*/

	
	
}
