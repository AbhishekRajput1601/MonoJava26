package com.abhi.constructorandinheritance.asg5;

class VehicleSystem {

    private Vehicle[] vehicles;
    private int totalVehicles;

    public VehicleSystem(int capacity) {
        vehicles = new Vehicle[capacity];
        totalVehicles = 0;
    }

    public void addVehicle(Vehicle vehicle) {

        if (totalVehicles < vehicles.length) {
            vehicles[totalVehicles] = vehicle;
            totalVehicles++;
        } else {
            System.out.println("Vehicle storage full");
        }
    }

    public void displayAllVehicles() {

        if (totalVehicles == 0) {
            System.out.println("No vehicle records");
            return;
        }

        for (int i = 0; i < totalVehicles; i++) {
            vehicles[i].displayDetails();
            System.out.println("----------------------");
        }
    }

    public void processVehicles() {

        if (totalVehicles == 0) {
            System.out.println("No vehicles to process");
            return;
        }

        double totalFee = 0;

        for (int i = 0; i < totalVehicles; i++) {

            double fee = vehicles[i].calculateTotalFee();

            System.out.println("Vehicle : " + vehicles[i].getRegistrationNumber()
                    + "  Fee : " + fee);

            totalFee += fee;
        }

        System.out.println("Total Fees Collected : " + totalFee);
    }
}