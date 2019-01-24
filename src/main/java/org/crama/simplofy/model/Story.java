package org.crama.simplofy.model;

import java.io.Serializable;
import java.time.LocalDateTime;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Story implements Serializable, Comparable<Story> {

	private static final long serialVersionUID = 86040465390500921L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.REFRESH, fetch = FetchType.LAZY) 
	@JoinColumn(name = "account_id", nullable = false)
	private Account account;
	
	@Column(nullable = false)
	private String storyName;

	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@Transient
	private String createdAgo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "story", cascade=CascadeType.ALL)
	private List<Chapter> chapters;
	
	
	public Story() {
		super();
	}
	
	public Story(Account account, String storyName) {
		super();
		this.account = account;
		this.storyName = storyName;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getStoryName() {
		return storyName;
	}

	public void setStoryName(String storyName) {
		this.storyName = storyName;
	}

	public List<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedAgo() {
		return createdAgo;
	}

	public void setCreatedAgo(String createdAgo) {
		this.createdAgo = createdAgo;
	}

	@Override
	public int compareTo(Story o) {
		return o.getCreatedAt().compareTo(this.getCreatedAt());
	}

	@Override
	public String toString() {
		return "Story [id=" + id + ", account=" + account + ", storyName=" + storyName
				+ ", createdAgo=" + createdAgo + ", chapters=" + chapters + "]";
	}
	
	
	
}
