package com.service.controller;

public class entity {
    private String hi;

    public String getHi() {
        return hi;
    }

    public void setHi(String hi) {
        this.hi = hi;
    }

    @Override
    public String toString() {
        return "{" +
                "\"hi\":\"" + hi + '\"' +
                '}';
    }
}
