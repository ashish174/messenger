package org.askumar.tutorial.services;

import org.askumar.tutorial.database.DatabaseClass;
import org.askumar.tutorial.model.Profile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ProfileService {
  Map<String, Profile> profiles = DatabaseClass.getProfiles();

  public ProfileService() {
    profiles.put("askumar", new Profile(0, "ashish", "kumar", "askumar"));
  }


  public List<Profile> getProfiles(){
    return new ArrayList<Profile>(profiles.values());
  }

  public Profile getProfile(String userName){
    return profiles.get(userName);
  }

  public Profile addProfile(Profile profile){
    if(profiles.get(profile.getUsername())!=null)
      return null;
    profile.setId(DatabaseClass.getNextProfileIdSeq());
    profiles.put(profile.getUsername(), profile);
    return profile;
  }

  public Profile updateProfile(Profile profile){
    String userName = profile.getUsername();
    if(profiles.get(userName)!=null){
      profile.setId(profiles.get(userName).getId());
      profiles.put(userName, profile);
      return profile;
    }
    return null;
  }

  public void removeProfile(String userName){
    profiles.remove(userName);
  }


}
