package com.msref.karyonjersey.model;

public class Topic {
	
	public Topic() {}
	
	public Topic(String topicName, String duration){
		this.topicName = topicName;
		this.duration = duration;
	}

	private String topicName;
	
	private String duration;

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
}
