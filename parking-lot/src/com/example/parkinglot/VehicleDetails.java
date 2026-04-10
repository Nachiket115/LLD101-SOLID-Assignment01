package com.example.parkinglot;

import java.util.Objects;

public final class VehicleDetails {
    private final String registrationNumber;
    private final VehicleType vehicleType;
    private final String ownerName;

    public VehicleDetails(String registrationNumber, VehicleType vehicleType, String ownerName) {
        this.registrationNumber = Objects.requireNonNull(registrationNumber, "registrationNumber");
        this.vehicleType = Objects.requireNonNull(vehicleType, "vehicleType");
        this.ownerName = Objects.requireNonNull(ownerName, "ownerName");
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public String toString() {
        return registrationNumber + " (" + vehicleType + ")";
    }
}
