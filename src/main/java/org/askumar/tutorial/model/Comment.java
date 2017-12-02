package org.askumar.tutorial.model;

import java.util.Date;

public class Comment {
  int id;
  String text;
  String author;
  Date createdOn;

  public Comment() {
  }

  public Comment(int id, String text, String author) {
    this.id = id;
    this.text = text;
    this.author = author;
    this.createdOn = new Date();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Date getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Date createdOn) {
    this.createdOn = createdOn;
  }

  @Override
  public String toString() {
    return "Comment{" +
        "id=" + id +
        ", text='" + text + '\'' +
        ", author='" + author + '\'' +
        ", createdOn=" + createdOn +
        '}';
  }
}
