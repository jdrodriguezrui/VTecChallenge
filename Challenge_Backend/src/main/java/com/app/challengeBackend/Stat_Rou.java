package com.app.challengeBackend;

import javax.persistence.Id;
import javax.validation.constraints.*;
import java.io.Serializable;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@Document(collection = "Stat_Rou")
@NoArgsConstructor
public class Stat_Rou implements Serializable {

    public Stat_Rou(@NotNull String stationId, @NotNull String routeId){
        this.stationId = stationId;
        this.routeId = routeId;

        this.avgWaitTimeInMin = 0.0f;
        this.entries = 0;
    }

    public Stat_Rou(@NotNull String stationId, @NotNull String routeId, int entries, float avgTime){
        this.stationId = stationId;
        this.routeId = routeId;

        this.avgWaitTimeInMin = avgTime;
        this.entries = entries;
    }

    public void addEntry(float entryTimeInMin){
        if(entries < Integer.MAX_VALUE){
            this.entries++;
        }
        float newAvg = avgWaitTimeInMin + (entryTimeInMin - avgWaitTimeInMin)/entries;
        this.avgWaitTimeInMin = newAvg;
    }

    @Id
    @NotNull
    private String id;

    @NotNull
    private String stationId;

    @NotNull
    private String routeId;

    @NotNull
    @PositiveOrZero
    private float avgWaitTimeInMin;

    @NotNull
    @PositiveOrZero
    private int entries;

}
