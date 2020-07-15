package com.app.challengeBackend;

import lombok.Data;

@Data
public class TimeRequest {
    String stationId;
    String routeId;
    float time;
}
