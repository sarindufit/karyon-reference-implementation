package com.msref.karyonjersey.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Singleton;
import com.msref.karyonjersey.model.Topic;

@Singleton
@Path("/topics")
public class TopicResource {

	  ObjectMapper objectMapper = new ObjectMapper();
	
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response getTopics(){
		  
		  List<Topic> topicList = new ArrayList<Topic>();
		  topicList.add(new Topic("Eureka", "1 Hour"));
		  topicList.add(new Topic("Zuul", "1 Hour"));
		  topicList.add(new Topic("Hystrix", "1 Hour"));
		  
          try {
			return Response.ok(objectMapper.writeValueAsString(topicList)).build();
 		  } catch (JsonProcessingException e) {
 			 return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		  }	  
	  }
}
