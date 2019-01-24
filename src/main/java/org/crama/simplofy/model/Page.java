package org.crama.simplofy.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.crama.simplofy.controller.dto.AddPageDto;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Page implements Serializable, Comparable<Page> {

	private static final long serialVersionUID = -6359764914294208088L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.REFRESH, fetch = FetchType.LAZY) 
	@JoinColumn(name = "chapter_id", nullable = false)
	private Chapter chapter;
	
	@Column(nullable = false)
	private int pageNumber;
	
	@Column(nullable = true)
	private String title;
	
	@Type(type="text")
	private String pageContent;

	public Page() {
		super();
	}
	
	public Page(AddPageDto addPageObj) {
		super();
		if (addPageObj.getId() != null) {
			this.id = addPageObj.getId();
		}
		this.pageNumber = addPageObj.getPageNumber();
		this.pageContent = addPageObj.getPageContent();
		this.title = addPageObj.getTitle();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getPageContent() {
		return pageContent;
	}

	public void setPageContent(String pageContent) {
		this.pageContent = pageContent;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int compareTo(Page o) {
		return this.getPageNumber() - o.getPageNumber();
	}

	@Override
	public String toString() {
		return "Page [id=" + id + ", chapter=" + chapter + ", pageNumber=" + pageNumber + ", title=" + title
				+ ", pageContent=" + pageContent + "]";
	}
	
	
}
