package com.techelevator.cardexample;

public class Pets {
    private int id;
    private int rating;
    private String animal;
    private String name;
    private String size;
    private String personality;
    private int age;
    private String description;
    private String location;
    private String favoriteToy;
    private String review;
    private boolean spayedNeutered;
    private String imageURL;


    public Pets(int id, int rating, String animal, String name,
                String size, String personality,
                int age, String description, String location,
                String favoriteToy, String review, boolean spayedNeutered, String imageURL) {
        this.id = id;
        this.rating = rating;
        this.animal = animal;
        this.name = name;
        this.size = size;
        this.personality = personality;
        this.age = age;
        this.description = description;
        this.location = location;
        this.favoriteToy = favoriteToy;
        this.review = review;
        this.spayedNeutered = spayedNeutered;
        this.imageURL = imageURL;
    }
}
}
