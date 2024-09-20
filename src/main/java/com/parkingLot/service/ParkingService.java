package com.parkingLot.service;

import com.parkingLot.controllers.dtos.VehiclesDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.Duration;

@Service
public class ParkingService {

    private final List<VehiclesDTO> PARKING = new ArrayList<>();

    public Optional<VehiclesDTO> seachVehicles(String licensePlate) {
        return PARKING.stream()
                .filter(vehiclesDTO -> vehiclesDTO.getLicensePlate().equalsIgnoreCase(licensePlate)).findFirst();
    }

    public VehiclesDTO createVehicles(String licensePlate){
        Optional<VehiclesDTO> carroDTOoptinal = seachVehicles(licensePlate);

        if (carroDTOoptinal.isPresent()){
            throw new RuntimeException("Veículo já está cadastrado");
        }
        VehiclesDTO vehiclesDTO = new VehiclesDTO();
        vehiclesDTO.setLicensePlate(licensePlate);
        vehiclesDTO.setEntryTime(LocalDateTime.now());
        PARKING.add(vehiclesDTO);

        return vehiclesDTO;
    }

    public VehiclesDTO registerExit(String placa){
        VehiclesDTO vehiclesDTO = seachVehicles(placa)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
        if (vehiclesDTO.getDepartureTime() != null) {
            throw new RuntimeException("Veículo já registrou saída");
        }
        vehiclesDTO.setDepartureTime(LocalDateTime.now());
        return vehiclesDTO;
    }

    public void deleteVehicles(String licensePlate){
        Optional<VehiclesDTO> optional = seachVehicles(licensePlate);
        if (optional.isEmpty()){
            throw new RuntimeException("Veiculo nao existe");
        }
        PARKING.remove(optional.get());
    }


    public static float levyOfTheVehicles(VehiclesDTO vehiclesDTO){
        LocalDateTime entryTime = vehiclesDTO.getEntryTime();
        LocalDateTime departureTime = LocalDateTime.now();

        Duration duration = Duration.between(entryTime, departureTime);
        long totalMinutes = duration.toMinutes();
        long totalHours = (totalMinutes + 59) / 60;

        float price = totalHours * VehiclesDTO.getCobrancaPorHora();
        return price;
    }

    public List<VehiclesDTO> getPARKING(){
        return PARKING;
    }

}


