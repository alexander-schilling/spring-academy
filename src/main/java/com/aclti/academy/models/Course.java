package com.aclti.academy.models;

import javax.persistence.*;
import java.util.List;

/**
 * Represents a Course, it has a title, description, image and a list of topics
 * @author Alexander Schilling
 */
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    private String imageUrl;
    @OneToMany(mappedBy = "course")
    private List<Topic> topics;

    // START: Getters & Setters

    public long getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public List<Topic> getTopics() { return topics; }
    public void setTopics(List<Topic> topics) { this.topics = topics; }

    // END: Getters & Setters
}
