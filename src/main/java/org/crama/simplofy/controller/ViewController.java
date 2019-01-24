package org.crama.simplofy.controller;

import java.util.Locale;

import org.crama.simplofy.error.NoIdException;
import org.crama.simplofy.model.Chapter;
import org.crama.simplofy.model.Page;
import org.crama.simplofy.model.Story;
import org.crama.simplofy.service.ChapterService;
import org.crama.simplofy.service.PageService;
import org.crama.simplofy.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {

	@Autowired
	private StoryService storyService;
	
	@Autowired
	private ChapterService chapterService;
	
	@Autowired
	private PageService pageService;
	
	@Autowired
	private MessageSource messages;
	
	@RequestMapping(value="/view", params = {"type", "id"}, method=RequestMethod.GET)
    public String view(@RequestParam String type, @RequestParam long id, Model model) throws NoIdException, NoSuchMessageException {
		
		switch (type) {
		case "story":
			Story story = storyService.findStoryById(id);
			if (story == null) {
				throw new NoIdException("error.noid", messages.getMessage("error.noid", null, Locale.ENGLISH));
			}
			model.addAttribute("story", story);
			break;
		case "chapter":
			Chapter chapter = chapterService.findChapterById(id);
			if (chapter == null) {
				throw new NoIdException("error.noid", messages.getMessage("error.noid", null, Locale.ENGLISH));
			}
			model.addAttribute("chapter", chapter);
			break;
		/*case "page":
			Page page = pageService.findPageById(id);
			if (page == null) {
				throw new NoIdException("error.noid", messages.getMessage("error.noid", null, Locale.ENGLISH));
			}
			model.addAttribute("page", page);
			break;*/
		default: 
			throw new NoIdException("error.noid", messages.getMessage("error.noid", null, Locale.ENGLISH));
			
		}
		
        return "view-overview";
    }
	
	
}
