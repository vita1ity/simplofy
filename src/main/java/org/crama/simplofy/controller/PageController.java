package org.crama.simplofy.controller;

import java.util.Locale;

import org.crama.simplofy.controller.dto.AddPageDto;
import org.crama.simplofy.error.NoIdException;
import org.crama.simplofy.error.NotAutorisedException;
import org.crama.simplofy.error.ObjectNotFoundException;
import org.crama.simplofy.error.UserNotAuthenticatedException;
import org.crama.simplofy.model.Account;
import org.crama.simplofy.model.Chapter;
import org.crama.simplofy.model.Page;
import org.crama.simplofy.service.ChapterService;
import org.crama.simplofy.service.PageService;
import org.crama.simplofy.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class PageController {

	@Autowired
	private ChapterService chapterService;
	
	@Autowired
	private PageService pageService;
	
	@Autowired
	private MessageSource messages;
	
	@Autowired
	private SecurityService securityService;
	
	//create edit page
	@RequestMapping(value="/page/create", method=RequestMethod.PUT)
	@ResponseBody
    public Page createPage(@RequestBody AddPageDto addPageObj) {
		
		Chapter chapter = chapterService.findChapterById(addPageObj.getChapterId());
		Page page = pageService.save(addPageObj, chapter);
		
        return page;
    }
	
	
	@RequestMapping(value="/create-page/{pageId}", method=RequestMethod.GET)
    public String createPageContent(@PathVariable Long pageId, Model model) 
    		throws ObjectNotFoundException, NoSuchMessageException, UserNotAuthenticatedException, NotAutorisedException {
		
		Page page = pageService.findPageById(pageId);
		if (page == null) {
			throw new ObjectNotFoundException("error.page.notFound", messages.getMessage("error.page.notFound", null, Locale.ENGLISH));
		}
		
		Account account = securityService.findLoggedInAccount();
		if (!page.getChapter().getStory().getAccount().equals(account)) {
			throw new NotAutorisedException("error.page.notAuthorised", messages.getMessage("error.page.notAuthorised", null, Locale.ENGLISH));
		}
		
		model.addAttribute(page);
		
        return "create-page";
    }
	
	
	//edit page
	@RequestMapping(value="/page/edit", method=RequestMethod.POST)
	@ResponseBody
    public Page editPage(@RequestBody AddPageDto addPageObj) throws NoIdException, NoSuchMessageException {
		
		if (addPageObj.getId() == null) {
			throw new NoIdException("error.noid", messages.getMessage("error.noid", null, Locale.ENGLISH));
		}
		
		//Chapter chapter = chapterService.findChapterById(addPageObj.getChapterId());
		
		Page oldPage = pageService.findPageById(addPageObj.getId());
		Page page = pageService.update(addPageObj, oldPage);
		
        return page;
    }
	
	//edit page with content
	/*@RequestMapping(value="/page/edit-content", method=RequestMethod.POST)
    public String editPageContent(@RequestBody AddPageDto addPageObj, Model model) throws NoIdException, NoSuchMessageException {
		
		if (addPageObj.getId() == null) {
			throw new NoIdException("error.noid", messages.getMessage("error.noid", null, Locale.ENGLISH));
		}
		
		Chapter chapter = chapterService.findChapterById(addPageObj.getChapterId());
		Page page = pageService.save(addPageObj, chapter);
		
		model.addAttribute(page);
		
        return "create-page";
    }*/
	
	@RequestMapping(value="/page/delete", method=RequestMethod.POST)
	@ResponseBody
    public String deletePage(@RequestBody Page page) throws NoIdException, NoSuchMessageException {
		
		if (page.getId() == null) {
			throw new NoIdException("error.noid", messages.getMessage("error.noid", null, Locale.ENGLISH));
		}
		pageService.delete(page.getId());
		
        return "success";
    }
	
	@RequestMapping(value="/page/delete-getnext", method=RequestMethod.POST)
	@ResponseBody
    public Page deleteAndGetNaxtPage(@RequestBody Page page) throws NoIdException, NoSuchMessageException {
		
		if (page.getId() == null) {
			throw new NoIdException("error.noid", messages.getMessage("error.noid", null, Locale.ENGLISH));
		}
		Page actualPage = pageService.findPageById(page.getId());
		Page nextPage = pageService.getNext(actualPage);
		
		pageService.delete(page.getId());
		
        return nextPage;
    }
	
	@RequestMapping(value="/page/next", method=RequestMethod.GET)
    public String naxtPage(@RequestParam Long pageId, Model model) throws NoIdException, NoSuchMessageException {
		
		Page page = pageService.findPageById(pageId);
		Page nextPage = pageService.getNext(page);
		
        return "redirect:/create-page/" + nextPage.getId();
    }
	
	@RequestMapping(value="/page/prev", method=RequestMethod.GET)
    public String prevPage(@RequestParam Long pageId, Model model) throws NoIdException, NoSuchMessageException {
		
		Page page = pageService.findPageById(pageId);
		Page prevPage = pageService.getPrev(page);
		
		/*if (prevPage == null) {
			return "redirect:/create-page/" + pageId + "?prev=false";
		}*/
		
        return "redirect:/create-page/" + prevPage.getId();
    }
	
	@RequestMapping(value="/page/has-prev", method=RequestMethod.POST)
	@ResponseBody
    public boolean hasPrev(@RequestBody AddPageDto addPageObj) throws NoIdException, NoSuchMessageException {
		if (addPageObj.getId() == null) {
			throw new NoIdException("error.noid", messages.getMessage("error.noid", null, Locale.ENGLISH));
		}
		
		Page page = pageService.findPageById(addPageObj.getId());
		
		boolean hasPrev = pageService.checkPrevExist(page);
		
        return hasPrev;
    }
	
	
	
	
}
