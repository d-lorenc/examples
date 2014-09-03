package me.lorenc.samples.javaoctet;

public interface _5DefaultMethod {

    void doAction();
    
    default void doDefaultAction() {
        System.out.println("I do default action");
    }
    
    public static void doStaticAction() {
        System.out.println("I do static action");
    }
    
}
