package com.msref.karyonjersey.module;

import com.google.inject.AbstractModule;
import com.msref.karyonjersey.service.CourseService;
import com.msref.karyonjersey.service.RibbonService;
import com.msref.karyonjersey.service.impl.CourseServiceImpl;
import com.msref.karyonjersey.service.impl.RibbonServiceImpl;

public class MainModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(CourseService.class).to(CourseServiceImpl.class);
		bind(RibbonService.class).to(RibbonServiceImpl.class);
	}

}
