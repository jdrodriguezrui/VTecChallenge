package com.app.challengeBackend.config;

import com.app.challengeBackend.StatRou_Repo;
import com.app.challengeBackend.Stat_Rou;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Component
public class DBInit implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DBInit.class);

    @Autowired
    private StatRou_Repo repository;

    @Override
    public void run(String... args) throws Exception {
        this.repository.deleteAll();

        List<Stat_Rou> entries = new ArrayList<Stat_Rou>();
        entries.add(new Stat_Rou(
                "Station A",
                "Route 1",
                10,
                15.5f
        ));
        entries.add(new Stat_Rou(
                "Station B",
                "Route 1",
                2,
                10f
        ));
        entries.add(new Stat_Rou(
                "Station C",
                "Route 1",
                3,
                20f
        ));
        entries.add(new Stat_Rou(
                "Station D",
                "Route 1",
                1,
                15f
        ));
        entries.add(new Stat_Rou(
                "Station A",
                "Route 2",
                5,
                10f
        ));
        entries.add(new Stat_Rou(
                "Station E",
                "Route 2",
                20,
                3f
        ));
        entries.add(new Stat_Rou(
                "Station F",
                "Route 2",
                2,
                20f
        ));
        entries.add(new Stat_Rou(
                "Station C",
                "Route 3",
                1,
                5f
        ));
        entries.add(new Stat_Rou(
                "Station D",
                "Route 3",
                1,
                5f
        ));
        entries.add(new Stat_Rou(
                "Station E",
                "Route 3",
                2,
                10f
        ));

        this.repository.saveAll(entries);

        repository.findAll().forEach((entry) -> {
            logger.info("{}", entry);
        });
    }
}
