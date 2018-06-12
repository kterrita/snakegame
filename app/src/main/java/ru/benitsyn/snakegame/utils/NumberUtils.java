package ru.benitsyn.snakegame.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Common number utils functions.
 * For example find all dividers of a number
 *
 * @author beleychev
 */
public class NumberUtils {

    private NumberUtils() {
    }

    /**
     * Returns all dividers of a particular number
     *
     * @param number number to find dividers
     * @return {@code List<Integer>} containing all dividers
     */
    public static List<Integer> getAllDividers(int number) {
        if (number == 0) {
            System.out.println("Number is 0, all possible dividers are infinite.");
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        result.add(1);
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                result.add(i);
            }
        }
        return result;
    }
}
