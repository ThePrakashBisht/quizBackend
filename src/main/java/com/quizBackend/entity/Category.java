package com.quizBackend.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;

@Embeddable
public class Category {

	@Id
	private int id;
	private String title;
	@Column(insertable=false, updatable=false)
	private Date created_at;
	@Column(insertable=false, updatable=false)
	private Date updated_at;
	private int clues_count;
	
	public Category() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public int getClues_count() {
		return clues_count;
	}
	public void setClues_count(int clues_count) {
		this.clues_count = clues_count;
	}

}
