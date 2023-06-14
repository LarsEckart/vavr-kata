package com.bil.katas.vavr.exercises;

import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.collection.Seq;
import io.vavr.collection.Vector;

public class Person {

    private final String firstName;
    private final String lastName;
    private final Seq<Pet> pets;

    public Person(String firstName, String lastName) {
        this(firstName, lastName, Vector.empty());
    }

    public Person(String firstName, String lastName, Seq<Pet> pets) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pets = pets;
    }

    public boolean named(String name) {
        return name.equals(firstName + " " + lastName);
    }

    public Map<PetType, Integer> getPetTypes() {
        return pets.groupBy(Pet::type)
                .map(e -> Map.entry(e._1, e._2.size()))
                .collect(HashMap.collector());
    }

    public boolean hasPetType(PetType type) {
        return getPetTypes().containsKey(type);
    }

    public Person addPet(PetType petType, String name, int age) {
        return new Person(firstName, lastName, pets.append(new Pet(petType, name, age)));
    }

    public boolean isPetPerson() {
        return this.getNumberOfPets() >= 1;
    }

    public int getNumberOfPets() {
        return this.pets.size();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Seq<Pet> getPets() {
        return pets;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
