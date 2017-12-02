package org.askumar.tutorial.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectDemo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InjectDemoResource {

  @GET
  @Path("/annotation")
  public String getAnnotation(@Context UriInfo uriInfo, @Context HttpHeaders httpHeaders){
    return "URL: "+uriInfo.getAbsolutePath()+ "  \nCookies: "+httpHeaders.getCookies();
  }
}
