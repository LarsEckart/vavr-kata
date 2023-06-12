package com.bil.katas.vavr.exercises;

import io.vavr.collection.Seq;
import io.vavr.collection.Vector;

public class Park {

    private final String name;
    private final Seq<PetType> authorizedPetTypes;

    public Park(String name) {
        this(name, Vector.empty());
    }

    public Park(String name, Seq<PetType> authorizedPetTypes) {
        this.name = name;
        this.authorizedPetTypes = authorizedPetTypes;
    }

    public Park addAuthorizedPetType(PetType petType) {
        return new Park(name, authorizedPetTypes.append(petType));
    }

    public String getName() {
        return name;
    }

    public Seq<PetType> getAuthorizedPetTypes() {
        return authorizedPetTypes;
    }
}
