package org.askumar.tutorial.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.*;

@XmlRootElement
public class Message {
  int id;
  String text;
  String author;
  Date createdOn;
  @XmlTransient Map<Integer, Comment> comments = new HashMap<>();
  List<Link> links = new ArrayList<>();

  public Message(){}

  public Message(int id, String text, String author) {
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

  @XmlTransient
  public Map<Integer, Comment> getComments() {
    return comments;
  }

  public void setComments(Map<Integer, Comment> comments) {
    this.comments = comments;
  }

  public List<Link> getLinks() {
    return links;
  }

  public void setLinks(List<Link> links) {
    this.links = links;
  }

  public void addLink(String link, String rel){
    Link newLink = new Link();
    newLink.setLink(link);
    newLink.setRel(rel);
    links.add(newLink);
  }

  @Override
  public String toString() {
    return "Message{" +
        "id=" + id +
        ", text='" + text + '\'' +
        ", author='" + author + '\'' +
        ", createdOn=" + createdOn +
        ", links=" + links +
        '}';
  }
}
