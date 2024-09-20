package com.parkingLot.controllers.dtos;


import java.time.LocalDateTime;

public class VehiclesDTO {
    private String licensePlate;
    private LocalDateTime entryTime;
    private LocalDateTime departureTime;
    private static float lexyOfTheVehicles = 0.10f;

    public VehiclesDTO() {
    }

    public static float getCobrancaPorHora() {
        return lexyOfTheVehicles;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public float getCobrancadocarro() {
        return lexyOfTheVehicles;
    }

    public void setCobrancadocarro(float cobrancadocarro) {
        this.lexyOfTheVehicles = cobrancadocarro;
    }
}
