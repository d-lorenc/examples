package me.lorenc.samples.javaoctet;

import java.util.Arrays;
import java.util.Comparator;

public class _1Lambda {

    public void runnable() {
        // before
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("I'm doing some stuff");
            }
        });

        // after
        new Thread(() -> System.out.println("I'm doing some stuff"));

        // after - do even more stuff
        new Thread(() -> {
            System.out.println("one stuff");
            System.out.println("two stuffs");
        });
    }

    public void comparator() {
        String[] array = new String[] {"b", "c", "a"};

        // before
        Arrays.sort(array, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });

        // after
        Arrays.sort(array, (String s1, String s2) -> s1.compareTo(s2));

        // even simpler - after
        Arrays.sort(array, (s1, s2) -> s1.compareTo(s2));
    }
    
}
