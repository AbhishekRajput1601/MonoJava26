package com.abhi.interfaceLearning.assignment.asg3;

public class Speaker implements Controllable {

    public void turnOn() {
        System.out.println("Speaker turned ON");
    }

    public void turnOff() {
        System.out.println("Speaker turned OFF");
    }

    public void setMode(String mode) {
        System.out.println("Speaker mode set to: " + mode);
    }
}