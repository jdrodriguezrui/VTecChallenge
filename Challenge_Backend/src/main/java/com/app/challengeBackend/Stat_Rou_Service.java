package com.app.challengeBackend;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Stat_Rou_Service {
    private StatRou_Repo repository;

    @Autowired
    public Stat_Rou_Service(StatRou_Repo repository) {
        this.repository = repository;
    }

    public List<Stat_Rou> findAll(){
        return this.repository.findAll();
    }

    public Stat_Rou addTimeEntry(float timeInMin, String stationId, String routeId){
        Stat_Rou entry = repository.findByStationIdAndRouteId(stationId,routeId);
        entry.addEntry(timeInMin);
        return this.repository.save(entry);
    }

    public List<String> getAllStations(){
        return repository.getAllStations();
    }

    public List<String> getStationRoutes(String stationId){
        return repository.getAllStationRoutes(stationId);
    }

    public float getAvgTimeInMin(String stationId, String routeId){
        Stat_Rou result = repository.findByStationIdAndRouteId(stationId,routeId);
        if(result != null){
            return result.getAvgWaitTimeInMin();
        }else{
            return 0f;
        }
    }
}
