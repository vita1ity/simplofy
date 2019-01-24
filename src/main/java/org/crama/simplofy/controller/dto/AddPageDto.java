package org.crama.simplofy.controller.dto;

import java.io.Serializable;

import javax.persistence.Column;

public class AddPageDto implements Serializable {

	private static final long serialVersionUID = 8419971864783302376L;

	private Long id;
	
	private Long chapterId;
	
	@Column(nullable = false)
	private int pageNumber;
	
	@Column(nullable = false)
	private String title;
	
	private String pageContent;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getChapterId() {
		return chapterId;
	}

	public void setChapterId(Long chapterId) {
		this.chapterId = chapterId;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPageContent() {
		return pageContent;
	}

	public void setPageContent(String pageContent) {
		this.pageContent = pageContent;
	}
	
	
}
