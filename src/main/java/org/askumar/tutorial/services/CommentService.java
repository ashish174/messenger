package org.askumar.tutorial.services;

import org.askumar.tutorial.database.DatabaseClass;
import org.askumar.tutorial.model.Comment;
import org.askumar.tutorial.model.ErrorMessage;
import org.askumar.tutorial.model.Message;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentService {
  Map<Integer, Message> messages = DatabaseClass.getMessages();

  public List<Comment> getComments(int messageId){
    return new ArrayList<>(messages.get(messageId).getComments().values());
  }

  public Comment getComment(int messageId, int commentId){
    ErrorMessage errorMessage = new ErrorMessage("Not Found", 404, "http://www.ashtutorial.com");
    Response response = Response.status(Status.NOT_FOUND)
        .entity(errorMessage)
        .build();

    Message message = messages.get(messageId);
    if(message==null)
      throw new WebApplicationException(response);
    Comment comment = message.getComments().get(commentId);
    if(comment == null)
      throw new NotFoundException(response);
    return comment;
  }

  public Comment postComment(int messageId, Comment comment){
    comment.setId(DatabaseClass.getNextCommentIdSeq());
    Message message = messages.get(messageId);
    if(message!=null){
      message.getComments().put(comment.getId(), comment);
      return message.getComments().get(comment.getId());
    }
    return null;
  }

  public Comment updateComment(int messageId, Comment comment){
    Message message = messages.get(messageId);
    if(message!=null){
      Map<Integer, Comment> comments = message.getComments();
      if(comments.get(comment)!=null){
        comments.put(comment.getId(), comment);
        return comments.get(comment.getId());
      }
    }
    return null;
  }

  public void removeComment(int messageId, int commentId){
    Message message = messages.get(messageId);
    if(message!=null){
      message.getComments().remove(commentId);
    }
  }

}
