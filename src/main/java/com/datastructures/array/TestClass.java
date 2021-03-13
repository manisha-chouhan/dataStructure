package com.datastructures.array;

public class TestClass {
    public static void main(String[] args) {
        System.out.println(new StringAdder().add("Hello", "World"));
        System.out.println(new IntegerAdder().add(1, 2));
    }

    static class  StringAdder implements Adder<String> {
        @Override
        public String add(String a, String b) {
            return a + "-" + b;
        }
    }

    static class  IntegerAdder implements Adder<Integer> {
        @Override
        public Integer add(Integer a, Integer b) {
            return a + b;
        }
    }

    interface Adder<T> { T add(T a, T b); }

}
