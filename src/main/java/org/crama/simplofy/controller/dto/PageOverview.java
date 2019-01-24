package org.crama.simplofy.controller.dto;

import org.crama.simplofy.model.Page;
import org.crama.simplofy.service.PageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PageOverview {

	private static final Logger logger = LoggerFactory.getLogger(PageOverview.class);
	
	private Long id;
	
	private String title;
	
	private int pageNumber;
	
	private String content1;
	
	private String image;
	
	private String content2;

	public PageOverview(Page page) {
		
		this.id = page.getId();
		this.title = page.getTitle();
		this.pageNumber = page.getPageNumber();
		
		String content = page.getPageContent();
		if (content != null && content.length() != 0) {
			/*if (content.indexOf("<img") != -1) {
				String image = content.substring(content.indexOf("<img"), content.indexOf("fr-draggable">"));
				String[] imageArr = image.split(" ");
				
				logger.info("imageBeforeProcessing: " + image);
				for (String i: imageArr) {
					if (i.contains("src")) {
						image = i.substring(5, i.length() - 1);
					}
				}
				
				logger.info("image: " + image);
				
				while (content.indexOf("<img") != -1) {
					String imageRemove = content.substring(content.indexOf("<img"), content.indexOf("</img>"));
					content = content.replace(imageRemove, "");
				}
				this.image = image;
			}*/
			
			/*if (content.length() > 800) {
				String contentStart = content.substring(0, 800); 
				this.content1 = contentStart + " ...</p></div>";
				
			}
			else {
				this.content1 = content;
			}*/
			
			this.content1 = content;
			logger.info(this.content1);
			/*if (content.length() > 750) {
				this.content2 = content.substring(650, 750);
			}
			else if (content.length() > 650 && content.length() > 750) {
				this.content2 = content.substring(650, content.length());
			}
			logger.info(this.content1);
			logger.info(this.content2);*/
		}
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent1() {
		return content1;
	}

	public void setContent1(String content1) {
		this.content1 = content1;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContent2() {
		return content2;
	}

	public void setContent2(String content2) {
		this.content2 = content2;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	@Override
	public String toString() {
		return "PageOverview [id=" + id + ", title=" + title + ", pageNumber=" + pageNumber + ", content1=" + content1
				+ ", image=" + image + ", content2=" + content2 + "]";
	}

	
}
