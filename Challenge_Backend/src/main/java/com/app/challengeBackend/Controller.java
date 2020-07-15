package com.app.challengeBackend;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")

@CrossOrigin(origins = "*")
public class Controller {

    private Stat_Rou_Service service;

    @Autowired
    public Controller(Stat_Rou_Service service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<String>> getStations(){
        List<String> stationNames = service.getAllStations();
        return ResponseEntity.ok(stationNames);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Stat_Rou>> getAll(){
        List<Stat_Rou> stationNames = service.findAll();
        return ResponseEntity.ok(stationNames);
    }

    @PostMapping("/routes")
    public ResponseEntity<List<String>> getStationRoutes(
            @RequestBody TimeRequest request){

        List<String> routeNames = service.getStationRoutes(request.stationId);
        if(routeNames.size() == 0){
            return new ResponseEntity<>(routeNames, HttpStatus.BAD_REQUEST);
        }else{
            return ResponseEntity.ok(routeNames);
        }
    }

    @PostMapping("/time")
    public ResponseEntity<Float> getTime(
            @RequestBody TimeRequest requestBody){


        float time = service.getAvgTimeInMin(requestBody.stationId,requestBody.routeId);
        if(time == 0f){
            return new ResponseEntity<>(0f, HttpStatus.NO_CONTENT);
        }else{
            return ResponseEntity.ok(time);
        }
    }

    @PostMapping("/entry")
    public ResponseEntity<Float> addEntry(
            @RequestBody TimeRequest requestBody){


        Stat_Rou newEntry = service.addTimeEntry(requestBody.time,requestBody.stationId,requestBody.routeId);
        if(newEntry == null){
            return new ResponseEntity<>(0f, HttpStatus.NO_CONTENT);
        }else{
            return ResponseEntity.ok(newEntry.getAvgWaitTimeInMin());
        }
    }

}
