package com.app.challengeBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class Stat_Rou_CustomRepoImpl implements Stat_Rou_CustomRepo {

    private final MongoOperations operations;
    private static final String COLLECTION = "Stat_Rou";

    @Autowired
    public Stat_Rou_CustomRepoImpl(MongoOperations operations){
        Assert.notNull(operations, "MongoOperations must not be null");
        this.operations = operations;
    }

    @Override
    public List<String> getAllStations() {
        return operations.findDistinct("stationId",Stat_Rou.class,String.class);
    }

    @Override
    public List<String> getAllStationRoutes(String stationId) {
        Criteria criteria = new Criteria().where("stationId").is(stationId);;
        Query query = new Query();
        query.addCriteria(criteria);
        List<Stat_Rou> result = operations.find(query,Stat_Rou.class);

        List<String> names = new ArrayList<>();
        for(Stat_Rou elem: result){
            names.add(elem.getRouteId());
        }
        return names;
    }

}
