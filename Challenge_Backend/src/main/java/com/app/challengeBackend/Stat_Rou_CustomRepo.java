package com.app.challengeBackend;

import javax.validation.constraints.NotBlank;
import java.util.List;

public interface Stat_Rou_CustomRepo {
    List<String> getAllStations();
    List<String> getAllStationRoutes(String stationId);
}
