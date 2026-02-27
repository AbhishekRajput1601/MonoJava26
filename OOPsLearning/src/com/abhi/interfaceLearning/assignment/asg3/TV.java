package com.abhi.interfaceLearning.assignment.asg3;

public class TV implements Controllable {

    public void turnOn() {
        System.out.println("TV turned ON");
    }

    public void turnOff() {
        System.out.println("TV turned OFF");
    }

    public void setMode(String mode) {
        System.out.println("TV mode set to: " + mode);
    }
}
