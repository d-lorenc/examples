package me.lorenc.samples.javaoctet;

@FunctionalInterface
public interface _2FunctionalInterface {

    void doFunction();

}

class UseFunctionalInterface {

    public UseFunctionalInterface(_2FunctionalInterface functionalInterface) {
        functionalInterface.doFunction();
    }

    public static void main(String[] args) {
        // before
        new UseFunctionalInterface(new _2FunctionalInterface() {
            @Override
            public void doFunction() {
                System.out.println("I do function");
            }
        });

        // after
        new UseFunctionalInterface(() -> System.out.println("I do function"));
    }

}