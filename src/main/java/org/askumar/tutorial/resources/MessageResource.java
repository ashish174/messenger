package org.askumar.tutorial.resources;

import org.askumar.tutorial.database.DatabaseClass;
import org.askumar.tutorial.exception.DataNotFoundException;
import org.askumar.tutorial.model.Message;
import org.askumar.tutorial.resources.beans.MessageFilterBean;
import org.askumar.tutorial.services.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by askumar on 11/11/17.
 */
@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
public class MessageResource {
  MessageService messageService = new MessageService();

  /*@GET
  public List<Message> getMessages(@QueryParam("year") int year,
                                   @QueryParam("start") int start,
                                   @QueryParam("size") int size){
    //return MessageService.getstaticMessages();
    if(year>0 && start >0 && size >0)
      return messageService.getMessagesPaginatedForYear(year, start, size);
    if(year > 0)
      return messageService.getMessagesForYear(year);
    if(start>0 && size > 0)
      return messageService.getMessagesPaginated(start, size);
    return messageService.getMessages();
  }*/

  @GET
  public List<Message> getMessages(@BeanParam MessageFilterBean messageFilterBean){
    //return MessageService.getstaticMessages();
    if(messageFilterBean.getYear()>0 && messageFilterBean.getStart() >0 && messageFilterBean.getSize() >0)
      return messageService.getMessagesPaginatedForYear(messageFilterBean.getYear(), messageFilterBean.getStart(), messageFilterBean.getSize());
    if(messageFilterBean.getYear() > 0)
      return messageService.getMessagesForYear(messageFilterBean.getYear());
    if(messageFilterBean.getStart()>0 && messageFilterBean.getSize() > 0)
      return messageService.getMessagesPaginated(messageFilterBean.getStart(), messageFilterBean.getSize());
    return messageService.getMessages();
  }

  @GET
  @Path("/{messageId}")
  public Message getMessage(@PathParam("messageId") int messageId, @Context UriInfo uriInfo){
    Message message =  messageService.getMessage(messageId);
    //setMessageLinks(uriInfo, message);
    return message;
  }

  @POST
  public Response addMessage(Message message, @Context UriInfo uriInfo){
    Message newMessage = messageService.addMessage(message);
    setMessageLinks(uriInfo, newMessage);
    String newMessageId = String.valueOf(newMessage.getId());
    URI newUri = uriInfo.getAbsolutePathBuilder().path(newMessageId).build();
    return Response.created(newUri)
        .entity(newMessage)
        .build();
  }

  @PUT
  @Path("/{messageId}")
  public Message updateMessage(@PathParam("messageId") int messageId, Message message){
    message.setId(messageId);
    return messageService.updateMessage(message);
  }

  @DELETE
  @Path("/{messageId}")
  public void deleteMessage(@PathParam("messageId") int id){
    messageService.deleteMessage(id);
  }


  @Path("/{messageId}/comments")
  public CommentResource getCommentResource(){
    return new CommentResource();
  }


  @Path("/{messageId}/likes")
  public LikesResource getLikesResource(){
    return new LikesResource();
  }

  String getURIForSelf(UriInfo uriInfo, Message message){
    return uriInfo.getBaseUriBuilder()
        .path(MessageResource.class)
        .path(String.valueOf(message.getId()))
        .build()
        .toString();
  }

  void setMessageLinks(UriInfo uriInfo, Message message){
    message.addLink(getURIForSelf(uriInfo, message), "self");
    message.addLink(getURIForProfile(uriInfo, message), "profile");
    message.addLink(getURIForComment(uriInfo, message), "comments");
  }

  String getURIForComment(UriInfo uriInfo, Message message){
    return uriInfo.getBaseUriBuilder()
        .path(MessageResource.class)
        .path(MessageResource.class, "getCommentResource")
        .resolveTemplate("messageId", message.getId())
        .build()
        .toString();
  }

  String getURIForProfile(UriInfo uriInfo, Message message){
    return uriInfo.getBaseUriBuilder()
        .path(ProfileResource.class)
        .path(message.getAuthor())
        .build()
        .toString();
  }

}
