package org.crama.simplofy.controller;

import java.util.Locale;

import org.crama.simplofy.controller.dto.AddChapterDto;
import org.crama.simplofy.error.NoIdException;
import org.crama.simplofy.model.Chapter;
import org.crama.simplofy.model.Story;
import org.crama.simplofy.service.ChapterService;
import org.crama.simplofy.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChapterController {
	
	@Autowired
	private ChapterService chapterService;
	
	@Autowired
	private StoryService storyService;

	@Autowired
	private MessageSource messages;
	
	@RequestMapping(value="/chapter/create", method=RequestMethod.PUT)
	@ResponseBody
	public Chapter createChapter(@RequestBody AddChapterDto addChapterObj) {
		
		Story story = storyService.findStoryById(addChapterObj.getStoryId());
		Chapter chapter = chapterService.saveChapter(addChapterObj, story);
		
        return chapter;
    }
	
	@RequestMapping(value="/chapter/edit", method=RequestMethod.POST)
	@ResponseBody
	public Chapter editChapter(@RequestBody AddChapterDto addChapterObj) throws NoIdException, NoSuchMessageException {
		
		Chapter oldChapter = chapterService.findChapterById(addChapterObj.getId());
		if (oldChapter == null) {
			throw new NoIdException("error.noid", messages.getMessage("error.noid", null, Locale.ENGLISH));
		}
		
		//Story story = storyService.findStoryById(addChapterObj.getStoryId());
		
		
		Chapter chapter = chapterService.update(addChapterObj, oldChapter);
		
        return chapter;
    }
	
	@RequestMapping(value="/chapter/delete", method=RequestMethod.POST)
	@ResponseBody
    public String deleteChapter(@RequestBody Chapter chapter) throws NoIdException, NoSuchMessageException {
		if (chapter.getId() == null) {
			throw new NoIdException("error.noid", messages.getMessage("error.noid", null, Locale.ENGLISH));
		}
		
		chapterService.delete(chapter.getId());
		
        return "success";
    }
	
}
