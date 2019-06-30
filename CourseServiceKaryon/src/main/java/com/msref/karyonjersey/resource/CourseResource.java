package com.msref.karyonjersey.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.google.inject.Singleton;

@Singleton
@Path("/courses")
public class CourseResource {

	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response getCourses(){
	        JSONObject response = new JSONObject();
	        try {
	            response.put("courseName", "Netflix Karyon");
	            return Response.ok(response.toString()).build();
	        } catch (JSONException e) {
	            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	        }		  
	  }
}
