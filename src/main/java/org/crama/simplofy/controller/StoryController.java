package org.crama.simplofy.controller;

import java.util.List;
import java.util.Locale;

import org.crama.simplofy.error.NoIdException;
import org.crama.simplofy.error.ObjectNotFoundException;
import org.crama.simplofy.error.UserNotAuthenticatedException;
import org.crama.simplofy.model.Account;
import org.crama.simplofy.model.Story;
import org.crama.simplofy.service.PageService;
import org.crama.simplofy.service.SecurityService;
import org.crama.simplofy.service.StoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StoryController {

	private static final Logger logger = LoggerFactory.getLogger(StoryController.class);
	
	@Autowired
	private StoryService storyService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private PageService pageService;
	
	@Autowired
	private MessageSource messages;
	
	@RequestMapping(value="/stories", method=RequestMethod.GET)
    public String showStoriesPage(Model model) 
    		throws ObjectNotFoundException, NoSuchMessageException, UserNotAuthenticatedException {
		
		logger.info("Stories Controller");
		
		Account account = securityService.findLoggedInAccount();
		
		List<Story> userStories = storyService.getUserStories(account);
		
		logger.info("User Stories: " + userStories);
		
		model.addAttribute("userStories", userStories);
		
        return "stories";
    }
	
	@RequestMapping(value="/story/create", method=RequestMethod.PUT)
	@ResponseBody
	public Story createStory(@RequestBody Story story) 
    		throws ObjectNotFoundException, NoSuchMessageException, UserNotAuthenticatedException {
		
		logger.info("Create Stor: " + story);
		
		Account account = securityService.findLoggedInAccount();
		
		Story savedStory = storyService.save(story, account);
		
        return savedStory;
    }
	
	@RequestMapping(value="/story/edit", method=RequestMethod.POST)
	@ResponseBody
	public Story editStory(@RequestBody Story story) 
    		throws ObjectNotFoundException, NoSuchMessageException, NoIdException, UserNotAuthenticatedException {
		
		if (story.getId() == null) {
			throw new NoIdException("error.noid", messages.getMessage("error.noid", null, Locale.ENGLISH));
		}
		
		Story oldStory = storyService.findStoryById(story.getId());
		Story savedStory = storyService.update(story, oldStory);
		
        return savedStory;
    }
	
	@RequestMapping(value="/story/delete", method=RequestMethod.POST)
	@ResponseBody
    public String deleteStory(@RequestBody Story story) throws NoIdException, NoSuchMessageException {
		
		if (story.getId() == null) {
			throw new NoIdException("error.noid", messages.getMessage("error.noid", null, Locale.ENGLISH));
		}
		
		storyService.delete(story.getId());
		
        return "success";
    }
	
	
	
}
