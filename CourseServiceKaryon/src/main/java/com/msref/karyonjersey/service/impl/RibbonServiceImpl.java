package com.msref.karyonjersey.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.msref.karyonjersey.service.RibbonService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.loadbalancer.Server;

public class RibbonServiceImpl implements RibbonService{

	@Inject
	EurekaClient eurekaClient;
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public List<Server> getTopicServiceServerList() {
		
		List<Server> serverList = new ArrayList<Server>();
		
		List<InstanceInfo> instanceInfoList = eurekaClient.getApplication("topic-service").getInstances();
		
		for (InstanceInfo instanceInfo : instanceInfoList) {
			serverList.add(new Server(instanceInfo.getVIPAddress(), instanceInfo.getPort()));
		}
		
		return serverList;
	}

}
