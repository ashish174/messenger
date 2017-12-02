package org.askumar.tutorial.database;

import org.askumar.tutorial.model.Message;
import org.askumar.tutorial.model.Profile;

import java.util.HashMap;
import java.util.Map;

public class DatabaseClass {
  private static Map<Integer, Message> messages = new HashMap<>();
  private static Map<String, Profile> profiles = new HashMap<>();
  private static int messageIdSeq = 0;
  private static int profileIdSeq = 0;
  private static int commentIdSeq = 0;

  public static Map<Integer, Message> getMessages() {
    return messages;
  }

  public static void setMessages(Map<Integer, Message> messages) {
    DatabaseClass.messages = messages;
  }

  public static Map<String, Profile> getProfiles() {
    return profiles;
  }

  public static void setProfiles(Map<String, Profile> profiles) {
    DatabaseClass.profiles = profiles;
  }

  public static int getNextMessageIdSeq(){
    return ++messageIdSeq;
  }

  public static int getNextProfileIdSeq(){
    return ++profileIdSeq;
  }

  public static int getNextCommentIdSeq(){
    return ++commentIdSeq;
  }


}
