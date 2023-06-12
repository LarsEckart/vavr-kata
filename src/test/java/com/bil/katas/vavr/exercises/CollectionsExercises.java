package com.bil.katas.vavr.exercises;

import io.vavr.collection.Map;
import io.vavr.collection.Seq;
import io.vavr.collection.Vector;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.bil.katas.vavr.exercises.PetType.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * By doing these exercises you should have learned about the following APIs.
 * <p/>
 * {@link Seq#map(Function)}<br>
 * {@link Seq#filter(Predicate)}<br>
 * {@link Seq#reject(Predicate)}<br>
 * {@link Seq#find(Predicate)}<br>
 * {@link Seq#forAll(Predicate)}<br>
 * {@link Seq#count(Predicate)}<br>
 * {@link Seq#map(Function)}<br>
 * {@link Seq#flatMap(Function)}<br>
 * <p>
 * {@link Seq#count(Predicate)}<br>
 * <p>
 */
public class CollectionsExercises extends PetDomainKata {

    @Test
    public void getFirstNamesOfAllPeople() {
        // Replace null, with a transformation method on Seq.
        Seq<String> firstNames = null;

        Seq<String> expectedFirstNames = Vector.of("Mary", "Bob", "Ted", "Jake", "Barry", "Terry", "Harry", "John");
        assertEquals(expectedFirstNames, firstNames);
    }

    @Test
    public void getNamesOfMarySmithsPets() {
        Person person = this.getPersonNamed("Mary Smith");
        Seq<Pet> pets = person.getPets();

        // Replace null, with a transformation method on Seq.
        Seq<String> names = null;

        assertEquals("Tabby", names.mkString());
    }

    @Test
    public void getPeopleWithCats() {
        // Replace null, with a positive filtering method on Seq.
        Seq<Person> peopleWithCats = null;

        assertEquals(peopleWithCats.size(), 2);
    }

    @Test
    public void getPeopleWithoutCats() {
        // Replace null, with a negative filtering method on Seq.
        Seq<Person> peopleWithoutCats = null;

        assertEquals(peopleWithoutCats.size(), 6);
    }

    @Test
    public void doAnyPeopleHaveCats() {
        // Replace null with a Predicate lambda which checks for PetType.CAT
        boolean doAnyPeopleHaveCats = false;
        assertTrue(doAnyPeopleHaveCats);
    }

    @Test
    public void doAllPeopleHavePets() {
        // Replace with a method call send to this.people that checks if all people have pets
        Predicate<Person> predicate = null;
        boolean result = people.forAll(predicate);
        assertFalse(result);
    }

    @Test
    public void howManyPeopleHaveCats() {
        // Replace 0 with the correct answer
        int count = 0;
        assertEquals(2, count);
    }

    @Test
    public void findMarySmith() {
        Person result = this.getPersonNamed("Mary Smith");
        assertEquals("Mary", result.getFirstName());
        assertEquals("Smith", result.getLastName());
    }

    @Test
    public void getPeopleWithPets() {
        // Replace with only the pets owners
        Seq<Person> petPeople = null;
        assertEquals(petPeople.size(), 7);
    }

    @Test
    public void getAllPetTypesOfAllPeople() {
        // Retrieve all pet types owned by the people
        Seq<PetType> petTypes = null;

        assertEquals(
                Vector.of(CAT, DOG, SNAKE, BIRD, TURTLE, HAMSTER),
                petTypes);
    }

    @Test
    public void howManyPersonHaveCats() {
        // Count the number of persons who owns cats
        int count = people.count(person -> person.hasPetType(PetType.CAT));
        assertEquals(2, count);
    }

    @Test
    public void whoOwnsTheYoungestPet() {
        // Find the person who owns the youngest pet
        Option<Person> person = null;
        assertEquals("Jake", person.get().getFirstName());
    }

    @Test
    public void whoOwnsTheOldestPet() {
        // Find the person who owns the oldest pet
        Option<Person> person = null;
        assertEquals("Ted", person.get().getFirstName());
    }

    @Test
    public void averagePetAge() {
        // Replace null by the average pet age
        Option<Double> average = null;
        assertEquals("1.89", new BigDecimal(average.get())
                .setScale(2, RoundingMode.HALF_EVEN).toPlainString());
    }

    @Test
    public void totalPetAge() {
        // Replace 0 by the total age of all the pets
        Number average = 0;
        assertEquals(17L, average);
    }

    @Test
    public void petsNameSorted() {
        // Sort pet names alphabetically
        String sortedPetNames = null;
        assertEquals("Dolly,Fuzzy,Serpy,Speedy,Spike,Spot,Tabby,Tweety,Wuzzy", sortedPetNames);
    }

    @Test
    public void sortByAge() {
        // Create a Seq<Integer> with ascending ordered age values.
        Seq<Integer> sortedAgeList = null;

        assertEquals(sortedAgeList.size(), 4);
        assertEquals(Vector.of(1, 2, 3, 4), sortedAgeList);
    }

    @Test
    public void sortByDescAge() {
        // Create a Seq<Integer> with descending ordered age values.
        Seq<Integer> sortedAgeList = null;

        assertEquals(sortedAgeList.size(), 4);
        assertEquals(Vector.of(4, 3, 2, 1), sortedAgeList);
    }

    @Test
    public void top3OlderPets() {
        // Get the names of the 3 older pets
        Seq<Pet> top3OlderPets = null;

        assertEquals(top3OlderPets.size(), 3);
        assertEquals(Vector.of("Spike", "Dolly", "Tabby"), top3OlderPets.map(Pet::name));
    }

    @Test
    public void getFirstPersonWithAtLeast2Pets() {
        // Find the first person who owns at least 2 pets
        Option<Person> firstPersonWithAtLeast2Pets = null;

        assertTrue(firstPersonWithAtLeast2Pets.isDefined());
        assertEquals("Bob", firstPersonWithAtLeast2Pets.get().getFirstName());
    }

    @Test
    public void isThereAnyPetOlderThan4() {
        // Check whether any pets older than 4 exists or not
        boolean isThereAnyPetOlderThan4 = false;

        assertFalse(isThereAnyPetOlderThan4);
    }

    @Test
    public void isEveryPetsOlderThan1() {
        // Check whether all pets are older than 1 or not
        boolean allOlderThan1 = false;

        assertTrue(allOlderThan1);
    }

    private Seq<String> filterParksFor(Seq<PetType> petTypes) {
        return this.parks.filter(park -> park.getAuthorizedPetTypes().containsAll(petTypes)).map(Park::getName);
    }

    @Test
    public void getListOfPossibleParksForAWalkPerPerson() {
        // For each person described as "firstName lastName" returns the list of names possible parks to go for a walk
        Map<String, Seq<String>> possibleParksForAWalkPerPerson = null;

        assertEquals(Vector.of("Jurassic", "Central", "Hippy"), possibleParksForAWalkPerPerson.get("John Doe").get());
        assertEquals(Vector.of("Jurassic", "Hippy"), possibleParksForAWalkPerPerson.get("Jake Snake").get());
    }
}
