package com.gateway.apigateway.Barber;

public class Barber {
    private Integer id;
    private String name;
    private Integer rating;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Integer getRating() { return rating; }

    public void setRating(Integer rating) { this.rating = rating; }
}