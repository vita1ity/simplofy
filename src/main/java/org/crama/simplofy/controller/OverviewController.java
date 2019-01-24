package org.crama.simplofy.controller;

import java.util.Locale;

import org.crama.simplofy.error.NoIdException;
import org.crama.simplofy.model.Chapter;
import org.crama.simplofy.model.Story;
import org.crama.simplofy.service.ChapterService;
import org.crama.simplofy.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OverviewController {

	@Autowired
	private StoryService storyService;
	
	@Autowired
	private ChapterService chapterService;
	
	@Autowired
	private MessageSource messages;
	
	@RequestMapping(value="/overview/{id}", method=RequestMethod.GET)
    public String overviewPage(@PathVariable Long id, @RequestParam(required = false) String type, Model model) throws NoIdException, NoSuchMessageException {
		
		if (type != null && type.equals("chapter")) {
			Chapter chapter = chapterService.findChapterById(id);
			if (chapter == null) {
				throw new NoIdException("error.noid", messages.getMessage("error.noid", null, Locale.ENGLISH));
			}
			model.addAttribute("chapter", chapter);
			
			
		}
		else {	
			Story story = storyService.findStoryById(id);
			if (story == null) {
				throw new NoIdException("error.noid", messages.getMessage("error.noid", null, Locale.ENGLISH));
			}
			
			model.addAttribute(story);
			
		}
		
		return "overview";
    }
	
}
