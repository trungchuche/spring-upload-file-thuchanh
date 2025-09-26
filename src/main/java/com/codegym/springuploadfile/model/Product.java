package com.codegym.springuploadfile.model;

public class Product {
    private int id;
    private String name;
    private String description;
    private String image;
    private String author;
    private String feedback;

    public Product() {
    }

    public Product(int id, String name, String description, String image, String author, String feedback) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.author = author;
        this.feedback = feedback;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", author='" + author + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
