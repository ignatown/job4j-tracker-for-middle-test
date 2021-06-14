package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int rsl = 0;
        int size = Math.min(left.length(), right.length());
        for (int i = 0; i < size; i++) {
            rsl = Character.compare(left.charAt(i), right.charAt(i));
            if (rsl != 0) {
                break;
            }
        }
        if (left.length() < right.length() && rsl == 0) {
            rsl = -1;
        } else if (left.length() > right.length() && rsl == 0) {
            rsl = 1;
        }
        return rsl;
    }
}