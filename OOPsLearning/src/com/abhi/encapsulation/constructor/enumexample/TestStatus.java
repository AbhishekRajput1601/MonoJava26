package com.abhi.encapsulation.constructor.enumexample;

enum Status {
    SUCCESS(200), ERROR(500);

    private int code;

    Status(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

public class TestStatus {
    public static void main(String[] args) {
        System.out.println(Status.SUCCESS);
        System.out.println(Status.SUCCESS.getCode());
    }
}