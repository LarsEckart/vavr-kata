package com.bil.katas.vavr.account;

import io.vavr.collection.Vector;

import java.util.NoSuchElementException;
import java.util.UUID;

public class UserService {

    public User findById(UUID id) throws NoSuchElementException {
        return repository.filter(p -> id.equals(p.getId())).single();
    }

    public void updateTwitterAccountId(UUID id, String twitterAccountId) {

    }

    private final Vector<User> repository = Vector.of(
            new User.Builder()
                    .id(UUID.fromString("376510ae-4e7e-11ea-b77f-2e728ce88125"))
                    .email("bud.spencer@gmail.com")
                    .name("Bud Spencer")
                    .password("OJljaefp0')")
                    .build(),
            new User.Builder()
                    .id(UUID.fromString("37651306-4e7e-11ea-b77f-2e728ce88125"))
                    .email("terrence.hill@gmail.com")
                    .name("Terrence Hill")
                    .password("àu__udsv09Ll")
                    .build());
}
