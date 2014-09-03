package me.lorenc.samples.javaoctet;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.junit.Test;

public class _3SimpleStream {

    private List<String> list = Arrays.asList("ab", "cc", "aa", "ba");

    @Test
    public void forLoopReplacement() {

        // before
        for (String string : list) {
            System.out.println(string);
        }

        // after
        list.stream().forEach(string -> System.out.println(string));

        // after after
        list.forEach(string -> System.out.println(string));
    }

    @Test
    public void filter() {
        
        // before
        for (String string : list) {
            if (!string.startsWith("a")) {
                System.out.println(string);
            }
        }
        
        // after
        list.stream()
            .filter(string -> !string.startsWith("a"))
            .forEach(string -> System.out.println(string));
    }

    @Test
    public void count() {
        long count = list.stream().filter(Objects::nonNull).count();
        
        assertThat(count, equalTo(4L));
    }
    
    @Test
    public void countDistinct() {
        long count = list.stream().distinct().count();

        assertThat(count, equalTo(4L));
    }
    
    @Test
    public void max() {
        Optional<String> maybeMaxStringOrMaybeNull = list.stream().max(String.CASE_INSENSITIVE_ORDER);

        String maxString = maybeMaxStringOrMaybeNull.get();
        
        assertThat(maxString, equalTo("cc"));
    }

}
