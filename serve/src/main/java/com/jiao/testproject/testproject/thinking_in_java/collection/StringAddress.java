package com.jiao.testproject.testproject.thinking_in_java.collection;

public class StringAddress {

    private String s;

    public StringAddress(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return super.toString() + " " + s + "\n";
    }


}
