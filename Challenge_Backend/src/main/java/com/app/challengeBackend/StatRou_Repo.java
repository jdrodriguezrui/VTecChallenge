package com.app.challengeBackend;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Repository
public interface StatRou_Repo extends MongoRepository<Stat_Rou, Serializable>, Stat_Rou_CustomRepo {
    Stat_Rou findByStationIdAndRouteId( String stationId,  String routeId);
    List<Stat_Rou> findByStationId(String stationId);
}
