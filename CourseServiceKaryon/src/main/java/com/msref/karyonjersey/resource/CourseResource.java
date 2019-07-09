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
import com.netflix.config.ConfigurationManager;

@Singleton
@Path("/courses")
public class CourseResource {

	  ObjectMapper mapper = new ObjectMapper();
	  
	  @Inject 
	  CourseService courseService;
	  
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response getCourses(){
		    
        try {
        	// Retrieving the topic list by invoking the topic microservice
	        List<Topic> topicList = courseService.getTopics();
	        
	        // ConfigurationManager.getConfigInstance() will load the properties from course-service.properties file. 
	        // @KaryonBootstrap(name = "course-service", healthcheck = HealthCheck.class) is how Karyon decides which property file to load
	        // As course-service is used for name attribute in KaryonBootstrap annotation, course-service.properties file will be loaded.  
	        Course course = new Course(ConfigurationManager.getConfigInstance().getString("courseName"), topicList);        	
			
	        return Response.ok(mapper.writeValueAsString(course)).build();
		} catch (IOException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	  }
}
