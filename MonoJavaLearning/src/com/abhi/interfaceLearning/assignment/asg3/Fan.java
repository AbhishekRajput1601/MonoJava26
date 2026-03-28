package com.abhi.interfaceLearning.assignment.asg3;

public class Fan implements Controllable {

    public void turnOn() {
        System.out.println("Fan turned ON");
    }

    public void turnOff() {
        System.out.println("Fan turned OFF");
    }

    public void setMode(String mode) {
        System.out.println("Fan mode set to: " + mode);
    }
}
