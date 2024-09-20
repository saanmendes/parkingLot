package com.parkingLot.controllers;

import com.parkingLot.controllers.dtos.LicensePlateDTO;
import com.parkingLot.controllers.dtos.VehiclesDTO;
import com.parkingLot.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @GetMapping
    public ResponseEntity<List<VehiclesDTO>> displayParking() {
        return ResponseEntity.ok(parkingService.getPARKING());
    }

    @PostMapping("/enter")
    public ResponseEntity<?> registerEntry(@RequestBody LicensePlateDTO licensePlateDTO) {
        try {
            parkingService.createVehicles(licensePlateDTO.getPlaca());
            return ResponseEntity.status(201).body("Veículo registrado com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/exit")
    public ResponseEntity<?> registerExit(@RequestBody LicensePlateDTO licensePlateDTO) {
        try {
            parkingService.registerExit(licensePlateDTO.getPlaca());
            return ResponseEntity.status(201).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteVehicles(@RequestBody LicensePlateDTO licensePlateDTO) {
        try {
            parkingService.deleteVehicles(licensePlateDTO.getPlaca());
            return ResponseEntity.status(201).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).build();
        }
    }

    @PostMapping("/levy")
    public ResponseEntity<?> levyOfTheVehicles(@RequestBody LicensePlateDTO licensePlateDTO) {
        try {
            VehiclesDTO vehiclesDTO = parkingService.seachVehicles(licensePlateDTO.getPlaca())
                    .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
            float price = ParkingService.levyOfTheVehicles(vehiclesDTO);
            return ResponseEntity.ok("Valor a ser cobrado: R$ " + price);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
