package org.crama.simplofy.service;

import java.util.List;

import org.crama.simplofy.controller.dto.AddChapterDto;
import org.crama.simplofy.model.Chapter;
import org.crama.simplofy.model.Story;
import org.crama.simplofy.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChapterService {

	@Autowired
	private ChapterRepository chapterRepository;
	
	public Chapter saveChapter(AddChapterDto addChapterObj, Story story) {
		
		List<Chapter> chapters = story.getChapters();
		int chapterNumber = chapters.size() + 1;
		
		Chapter chapter = new Chapter(addChapterObj);
		chapter.setChapterNumber(chapterNumber);
		chapter.setStory(story);
		
		Chapter saved = chapterRepository.save(chapter);
		
		return saved;
	}

	public Chapter findChapterById(long chapterId) {
		
		return chapterRepository.findOne(chapterId);
	}

	public void delete(long id) {
		
		chapterRepository.delete(id);
		
	}

	public Chapter update(AddChapterDto addChapterObj, Chapter oldChapter) {
		
		oldChapter.setChapterName(addChapterObj.getChapterName());
		
		return chapterRepository.save(oldChapter);
	}

	
	
}
