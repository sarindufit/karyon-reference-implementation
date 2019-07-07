package com.msref.karyonjersey.service.impl;

import java.util.List;

import com.google.inject.Inject;
import com.msref.karyonjersey.hystrix.HystrixGetTopicsRequestCommand;
import com.msref.karyonjersey.model.Topic;
import com.msref.karyonjersey.service.CourseService;
import com.msref.karyonjersey.service.RibbonService;

public class CourseServiceImpl implements CourseService{

	@Inject
	RibbonService ribbonService;
	
	@Override
	public List<Topic> getTopics() {
		List<Topic> topics = new HystrixGetTopicsRequestCommand(ribbonService).execute();
		return topics;
	}


}
