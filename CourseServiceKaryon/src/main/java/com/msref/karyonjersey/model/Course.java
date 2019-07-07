package com.msref.karyonjersey.model;

import java.util.List;

public class Course {
	
	public Course(String name) {
		this.name = name;
	}
	
	public Course(String name, List<Topic> topics) {
		this.name = name;
		this.topics = topics;
	}	

	private String name;
	
	private List<Topic> topics;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}
}
