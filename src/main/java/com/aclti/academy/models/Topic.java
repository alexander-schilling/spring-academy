package com.aclti.academy.models;

import javax.persistence.*;

/**
 * Represents a Topic, it has and id, title, description and content.
 * @author Alexander Schilling
 */
@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    private String content;
    @ManyToOne(cascade = { CascadeType.REMOVE })
    @JoinColumn(name = "course_id")
    private Course course;

    // START: Getters & Setters

    public long getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    // END: Getters & Setters
}
