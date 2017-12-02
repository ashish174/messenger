package org.askumar.tutorial.resources;

import org.askumar.tutorial.model.Profile;
import org.askumar.tutorial.services.ProfileService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.List;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {
  ProfileService profileService = new ProfileService();

  @GET
  public List<Profile> getProfiles(){
    return profileService.getProfiles();
  }

  @GET
  @Path("/{userName}")
  public Profile getProfile(@PathParam("userName") String userName){
    return profileService.getProfile(userName);
  }

  @POST
  public Profile addProfile(Profile profile){
    return profileService.addProfile(profile);
  }

  @PUT
  @Path("/{userName}")
  public Profile updateProfile(@PathParam("userName") String userName, Profile profile){
    profile.setUsername(userName);
    return profileService.updateProfile(profile);
  }

  @DELETE
  @Path("/{userName}")
  public void removeProfile(@PathParam("userName") String userName){
    profileService.removeProfile(userName);
  }
}
