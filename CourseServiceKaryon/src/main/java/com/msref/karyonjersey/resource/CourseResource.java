package com.msref.karyonjersey.resource;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.msref.karyonjersey.model.Course;
import com.msref.karyonjersey.model.Topic;
import com.msref.karyonjersey.service.CourseService;

@Singleton
@Path("/courses")
public class CourseResource {

	  ObjectMapper mapper = new ObjectMapper();
	  
	  @Inject 
	  CourseService courseService;
	  
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response getCourses(){
		  
	        List<Topic> topicList = courseService.getTopics();
	        Course course = new Course("Netflix Karyon", topicList);
	        
	            try {
					return Response.ok(mapper.writeValueAsString(course)).build();
				} catch (IOException e) {
					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
				}
	  }
}
