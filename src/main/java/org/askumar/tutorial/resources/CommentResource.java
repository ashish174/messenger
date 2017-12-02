package org.askumar.tutorial.resources;

import org.askumar.tutorial.model.Comment;
import org.askumar.tutorial.services.CommentService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {
  CommentService commentService = new CommentService();

  @GET
  public List<Comment> getComments(@PathParam("messageId") int messageId, @Context UriInfo uriInfo){
    return commentService.getComments(messageId);
  }

  @GET
  @Path("/{commentId}")
  public Comment getComment(@PathParam("messageId") int messageId, @PathParam("commentId") int commentId){
    return commentService.getComment(messageId, commentId);
  }

  @POST
  public Comment postComment(@PathParam("messageId") int messageId, Comment comment){
    return commentService.postComment(messageId, comment);
  }

  @PUT
  @Path("/{commentId}")
  public Comment updateComment(@PathParam("messageId") int messageId, @PathParam("commentId")int commentId, Comment comment){
    comment.setId(commentId);
    return commentService.updateComment(messageId, comment);
  }

  @DELETE
  @Path("/{commentId}")
  public void removeComment(@PathParam("messageId")int messageId, @PathParam("commentId")int commentId){
    commentService.removeComment(messageId, commentId);
  }
}
