package me.lorenc.samples.javaoctet;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class _4MethodReference {

    private List<String> list = Arrays.asList("b", "c", "a");

    @Test
    public void evenSimplerPrint() {

        // before
        list.forEach(string -> System.out.println(string));

        // after
        list.stream().forEach(System.out::println);
    }
    
    @Test
    public void staticMethod() {
        list.stream().forEach(_4MethodReference::staticPrint);
    }

    private static void staticPrint(String string) {
        System.out.println(string);
    }

    @Test
    public void instanceMethod() {
        list.stream().forEach(this::anotherPrint);
    }
    
    private void anotherPrint(String string) {
        System.out.println(string);
    }
    
    @Test
    public void constructor() {
        list.stream().forEach(SelfPrinting::new);
    }

    @Test
    public void methodOnTargetObject() {
        list.stream().forEach(String::toUpperCase);
    }
    
    class SelfPrinting {
        public SelfPrinting(String string) {
            System.out.println(string);
        }
    }
    
}
