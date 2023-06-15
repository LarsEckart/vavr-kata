package com.bil.katas.vavr.exercises;

import io.vavr.control.Try;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * By doing these exercises you should have learned :
 * {@link Try#get()}<br>
 * {@link Try#isSuccess()}<br>
 * {@link Try#isEmpty()}<br>
 * {@link Try#isFailure()}<br>
 * {@link Try#getOrElse(Object)}<br>
 * {@link Try#onFailure(Consumer)}<br>
 * {@link Try#onSuccess(Consumer)}<br>
 * {@link Try#flatMap(Function)}<br>
 */
public class TryExercises extends PetDomainKata {

    private final String SUCCESS_MESSAGE = "I m a fucking genius the result is ";

    private Try<Integer> Divide(Integer x, Integer y) {
        return Try.of(() -> x / y);
    }

    @Test
    public void getTheResultOfDivide() {
        // Divide x = 9 by y = 2
        Try<Integer> tryResult = Divide(9, 2);
        Integer result = tryResult.get();

        assertEquals(4, result, 0);
        assertTrue(tryResult.isSuccess());
        Assertions.assertFalse(tryResult.isEmpty());
        Assertions.assertFalse(tryResult.isFailure());
    }

    @Test
    public void mapTheResultOfDivide() {
        // Divide x = 9 by y = 2 and add z to the result
        Integer z = 3;
        Integer result = Divide(9, 2).map(r -> r + z).get();

        assertEquals(7, result, 0);
    }

    // expecting ArithmeticException
    @Test
    public void divideByZeroIsAlwaysAGoodIdea() {
        // Divide x by 0 and get the result
        int result = Divide(42, 0).getOrElseTry(() -> 0);
        assertEquals(0, result);
    }

    @Test
    public void divideByZeroOrElse() {
        // Divide x by 0, on exception returns 0
        Integer x = 1;
        Integer result = Divide(x, 0).getOrElse(0);

        assertEquals(0, result, 0);
    }

    @Test
    public void mapTheFailure() {
        // Divide x by 0, log the failure message to the console and get 0
        Integer x = 1;

        Integer result = Divide(x, 0)
                .onFailure(e -> System.out.println("Error : " + e.getMessage()))
                .getOrElse(0);

        assertEquals(0, result, 0);
    }

    @Test
    public void mapTheSuccess() {
        // Divide x by y
        // log the failure message to the console
        // Log your success to the console
        // Get the result or 0 if exception
        Integer x = 8;
        Integer y = 4;

        Integer result = Divide(x,y).onFailure(e -> System.out.println("Error : " + e.getMessage()))
                .onSuccess(r -> System.out.println(SUCCESS_MESSAGE + r))
                .getOrElse(0);

        assertEquals(2, result, 0);
    }

    @Test
    public void chainTheTry() {
        // Divide x by y
        // Chain 2 other calls to divide with x = previous Divide result
        // log the failure message to the console
        // Log your success to the console
        // Get the result or 0 if exception
        Integer x = 27;
        Integer y = 3;

        Integer result = 0;

        assertEquals(1, result, 0);
    }
}
