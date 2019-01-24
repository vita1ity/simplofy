package org.crama.simplofy.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.crama.simplofy.controller.dto.AddChapterDto;
import org.crama.simplofy.controller.dto.PageOverview;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Chapter implements Serializable, Comparable<Chapter> {

	private static final long serialVersionUID = -6141274762302009643L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.REFRESH, fetch = FetchType.LAZY) 
	@JoinColumn(name = "story_id", nullable = false)
	private Story story;
	
	@Column(nullable = false)
	private String chapterName;
	
	@Column(nullable = false)
	private int chapterNumber;

	@JsonIgnore
	@OneToMany(mappedBy = "chapter", cascade=CascadeType.ALL)
	private List<Page> pages;
	
	
	public Chapter() {}
	
	public Chapter(AddChapterDto addChapterObj) {
		if (addChapterObj.getId() != null) {
			this.id = addChapterObj.getId();
		}
		this.chapterName = addChapterObj.getChapterName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Story getStory() {
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
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

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	

	@Override
	public int compareTo(Chapter o) {
		return this.getChapterNumber() - o.chapterNumber;
	}

	
}
