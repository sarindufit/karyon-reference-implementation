package com.msref.karyonjersey.hystrix;

import io.netty.buffer.ByteBuf;
import io.reactivex.netty.protocol.http.client.HttpClientRequest;
import io.reactivex.netty.protocol.http.client.HttpClientResponse;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msref.karyonjersey.model.Topic;
import com.msref.karyonjersey.service.RibbonService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.Server;
import com.netflix.ribbon.transport.netty.RibbonTransport;
import com.netflix.ribbon.transport.netty.http.LoadBalancingHttpClient;

public class HystrixGetTopicsRequestCommand extends HystrixCommand<List<Topic>>{
	
	RibbonService ribbonService;
	
	ObjectMapper mapper = new ObjectMapper();
	
	public HystrixGetTopicsRequestCommand(RibbonService ribbonService) {
		super(HystrixCommandGroupKey.Factory.asKey("TopicServiceGroup"));
		this.ribbonService = ribbonService;
	}
	
	@Override
	protected List<Topic> run() throws Exception {
		List<Server> serverList = ribbonService.getTopicServiceServerList();
		
		BaseLoadBalancer lb = LoadBalancerBuilder.newBuilder().buildFixedServerListLoadBalancer(serverList);
		LoadBalancingHttpClient<ByteBuf, ByteBuf> client = RibbonTransport.newHttpClient(lb);
		HttpClientRequest<ByteBuf> request = HttpClientRequest.createGet("/topics");
		HttpClientResponse<ByteBuf> response = client.submit(request).toBlocking().first();
		
		String responseJson = response.getContent().toBlocking().first().toString(Charset.forName("UTF-8" ));
		Topic[] topicList = null;

    	topicList = mapper.readValue(responseJson, Topic[].class);
		
		return Arrays.asList(topicList);		
	}

	@Override
	protected List<Topic> getFallback() {
		List<Topic> emptyTopicList = new ArrayList<>(); 
		return emptyTopicList;
	}	
}
