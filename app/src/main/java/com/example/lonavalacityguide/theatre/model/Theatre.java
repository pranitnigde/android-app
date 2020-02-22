package com.example.lonavalacityguide.theatre.model;

public class Theatre {
    String name,contact,rating,email,address,description,mapid;

    public Theatre(String name, String contact, String rating, String email, String address, String description, String mapid) {
        this.name = name;
        this.contact = contact;
        this.rating = rating;
        this.email = email;
        this.address = address;
        this.description = description;
        this.mapid=mapid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMapid() {
        return mapid;
    }

    public void setMapid(String mapid) {
        this.mapid = mapid;
    }
}
