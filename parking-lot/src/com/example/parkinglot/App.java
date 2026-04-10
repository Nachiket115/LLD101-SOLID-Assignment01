package com.example.parkinglot;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        ParkingLotService parkingLot = new ParkingLotService(
                List.of(
                        new ParkingSlot("S1", 0, SlotType.SMALL, Map.of("G1", 5, "G2", 20)),
                        new ParkingSlot("M1", 0, SlotType.MEDIUM, Map.of("G1", 7, "G2", 8)),
                        new ParkingSlot("M2", 1, SlotType.MEDIUM, Map.of("G1", 3, "G2", 16)),
                        new ParkingSlot("L1", 1, SlotType.LARGE, Map.of("G1", 15, "G2", 4)),
                        new ParkingSlot("L2", 2, SlotType.LARGE, Map.of("G1", 11, "G2", 6))
                ),
                List.of(new EntryGate("G1"), new EntryGate("G2")),
                new NearestSlotAssignmentStrategy(),
                new BillingService(Map.of(
                        SlotType.SMALL, 30.0,
                        SlotType.MEDIUM, 60.0,
                        SlotType.LARGE, 120.0
                ))
        );

        LocalDateTime morning = LocalDateTime.of(2026, 4, 10, 9, 0);

        System.out.println("Initial status: " + parkingLot.status());

        ParkingTicket bikeTicket = parkingLot.park(
                new VehicleDetails("KA-01-BK-1001", VehicleType.TWO_WHEELER, "Aman"),
                morning,
                SlotType.MEDIUM,
                "G1"
        );
        System.out.println(bikeTicket);

        ParkingTicket carTicket = parkingLot.park(
                new VehicleDetails("KA-01-CA-2020", VehicleType.CAR, "Riya"),
                morning.plusMinutes(10),
                SlotType.MEDIUM,
                "G2"
        );
        System.out.println(carTicket);

        System.out.println("After parking: " + parkingLot.status());

        ParkingBill bill = parkingLot.exit(bikeTicket, morning.plusHours(2).plusMinutes(15));
        System.out.println(bill);

        System.out.println("After bike exit: " + parkingLot.status());
    }
}
