package com.msref.karyonjersey.service;

import java.util.List;

import com.netflix.loadbalancer.Server;

public interface RibbonService {

	public List<Server> getTopicServiceServerList();
	
}
