package org.crama.simplofy.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

import org.crama.simplofy.model.Account;
import org.crama.simplofy.model.Story;
import org.crama.simplofy.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoryService {

	@Autowired
	private StoryRepository storyRepository;
	
	public List<Story> getUserStories(Account account) {
		
		List<Story> stories = storyRepository.findByAccount(account); 
		
		Collections.sort(stories);
		
		for (Story story: stories) {
			
			story = calculateCreatedAgo(story);
			
		}
		
		return stories;
	}

	public Story createStory(String storyName, Account account) {
		
		Story story = new Story(account, storyName);
		Story savedStory = storyRepository.save(story);
		
		return savedStory;
	}

	public Story findStoryById(Long id) {
		return storyRepository.findOne(id);
	}

	public Story save(Story story, Account account) {
		
		story.setAccount(account);
		story.setCreatedAt(LocalDateTime.now());
		Story savedStory = storyRepository.save(story);
		savedStory = calculateCreatedAgo(savedStory);
		
		return savedStory;
		
	}

	public void delete(long id) {
		
		storyRepository.delete(id);
		
	}
	
	public void delete(Story story) {
		
		storyRepository.delete(story);
		
	}

	
	private Story calculateCreatedAgo(Story story) {
		LocalDateTime fromDateTime = story.getCreatedAt();
		LocalDateTime toDateTime = LocalDateTime.now();

		long years = fromDateTime.until( toDateTime, ChronoUnit.YEARS);
		long months = fromDateTime.until( toDateTime, ChronoUnit.MONTHS);
		long days = fromDateTime.until( toDateTime, ChronoUnit.DAYS);
		long hours = fromDateTime.until( toDateTime, ChronoUnit.HOURS);
		long minutes = fromDateTime.until( toDateTime, ChronoUnit.MINUTES);
		long seconds = fromDateTime.until( toDateTime, ChronoUnit.SECONDS);
		
		if (years != 0l) {
			story.setCreatedAgo(years + " yrs ago");
			return story;
		}
		else if (months != 0l) {
			story.setCreatedAgo(months + " mm ago");
			return story;
		}
		else if (days != 0l) {
			story.setCreatedAgo(days + " days ago");
			return story;
		}
		else if (hours != 0l) {
			story.setCreatedAgo(hours + " hrs ago");
			return story;
		}
		else if (minutes != 0l) {
			story.setCreatedAgo(minutes + " mins ago");
			return story;
		}
		else if (seconds != 0l) {
			story.setCreatedAgo(seconds + " sec ago");
			return story;
		}
		else {
			story.setCreatedAgo(0 + " sec ago");
		}
		
		return story;
		
	}

	public Story update(Story story, Story oldStory) {
		
		oldStory.setStoryName(story.getStoryName());
		storyRepository.save(oldStory);
		return oldStory;
	}
	
	
}

