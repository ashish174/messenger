package org.askumar.tutorial.services;

import org.askumar.tutorial.database.DatabaseClass;
import org.askumar.tutorial.exception.DataNotFoundException;
import org.askumar.tutorial.model.Message;

import java.util.*;

public class MessageService {
  Map<Integer, Message> messages = DatabaseClass.getMessages();

  public MessageService() {
    messages.put(0, new Message(0, "Default Message", "System"));
  }

  public List<Message> getstaticMessages(){
    Message m1 = new Message(1, "This is Ashish", "AK");
    Message m2 = new Message(2, "This ias damn good", "Ashsi");
    Message m3 = new Message(3, "This ias not satisfactort", "Raka");
    Message m4 = new Message(4, "TFiuck p", "Rambo");
    List<Message> messages = new ArrayList<>();
    messages.addAll(Arrays.asList(m1, m2, m3, m4));
    return messages;
  }

  public List<Message> getMessages(){
    return new ArrayList<Message>(messages.values());
  }

  public List<Message> getMessagesForYear(int year){
    List<Message> messagesInYear = new ArrayList<>();
    if(year > 0){
      Calendar cal = Calendar.getInstance();
      for(Message message : messages.values()){
        cal.setTime(message.getCreatedOn());
        if(cal.get(Calendar.YEAR)==year){
          messagesInYear.add(message);
        }
      }
    }
    return messagesInYear;
  }

  public List<Message> getMessagesPaginated(int start, int size){
    ArrayList<Message> paginatedMessages = new ArrayList<>(messages.values());
    if((start+size)>paginatedMessages.size())
        return new ArrayList<Message>();
    return paginatedMessages.subList(start, start+size);
    /*if(paginatedMessages.size() <= (start+size))
      return paginatedMessages.subList(start, start+size);
    return paginatedMessages.subList(start, paginatedMessages.size());*/
  }

  public List<Message> getMessagesPaginatedForYear(int year, int start, int size){
    List<Message> messagesInYear = getMessagesForYear(year);
    ArrayList<Message> paginatedMessages = new ArrayList<>(messagesInYear);
    if(paginatedMessages.size() <= (start+size))
      return paginatedMessages.subList(start, start+size);
    return paginatedMessages;
  }

  public Message getMessage(int id){
    if(messages.get(id)==null)
      throw new DataNotFoundException("Message with Id "+id+" Not Found");
    return messages.get(id);
  }

  public Message addMessage(Message message){
    int messageId = DatabaseClass.getNextMessageIdSeq();
    message.setId(messageId);
    message.setCreatedOn(new Date());
    messages.put(messageId, message);
    return message;
  }

  public Message updateMessage(Message message){
    if(messages.get(message.getId())!=null) {
      messages.put(message.getId(), message);
      return message;
    }
    else
      //return addMessage(message);
    return null;
  }

  public void deleteMessage(int messageId){
    if(messages.get(messageId)!=null) {
      messages.remove(messageId);
    }
  }
}
