package org.crama.simplofy.controller.dto;

import java.io.Serializable;

public class AddChapterDto implements Serializable {

	private static final long serialVersionUID = -1347935282408650822L;
	
	private Long id;
	
	private long storyId;
	
	private String chapterName;
	
	private int chapterNumber;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getStoryId() {
		return storyId;
	}

	public void setStoryId(long storyId) {
		this.storyId = storyId;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public int getChapterNumber() {
		return chapterNumber;
	}

	public void setChapterNumber(int chapterNumber) {
		this.chapterNumber = chapterNumber;
	}
	
	
	
}
