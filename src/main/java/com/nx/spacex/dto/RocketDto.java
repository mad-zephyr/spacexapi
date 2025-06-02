package com.nx.spacex.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class RocketDto {
    private Dimension height;
    private Dimension diameter;
    private Mass mass;
    private FirstStage firstStage;
    private SecondStage secondStage;
    private Engines engines;
    private LandingLegs landingLegs;
    private List<PayloadWeight> payloadWeights;

    private List<String> flickrImages;

    private String name;
    private String type;                // "rocket"
    private boolean active;
    private int stages;
    private int boosters;
    private long costPerLaunch;
    private int successRatePct;
    private LocalDate firstFlight;         // ISO yyyy-MM-dd; при желании → LocalDate
    private String country;
    private String company;
    private String wikipedia;
    private String description;
    private String id;


    @Data
    public static class Dimension {
        private Double meters;  // nullable
        private Double feet;
    }


    @Data
    public static class Mass {
        private long kg;
        private long lb;
    }


    @Data
    public static class Thrust {
        private int kN;
        private long lbf;
    }


    @Data
    public static class FirstStage {
        private Thrust thrustSeaLevel;
        private Thrust thrustVacuum;
        private boolean reusable;
        private int engines;
        private double fuelAmountTons;
        private Integer burnTimeSec;  // nullable
    }


    @Data
    public static class PayloadFairing {
        private Dimension height;
        private Dimension diameter;
    }


    @Data
    public static class SecondStagePayloads {
        private PayloadFairing compositeFairing;
        private String option1;
    }


    @Data
    public static class SecondStage {
        private Thrust thrust;
        private SecondStagePayloads payloads;
        private boolean reusable;
        private int engines;
        private double fuelAmountTons;
        private Integer burnTimeSec;
    }


    @Data
    public static class ISP {
        private int seaLevel;
        private int vacuum;
    }


    @Data
    public static class Engines {
        private ISP isp;
        private Thrust thrustSeaLevel;
        private Thrust thrustVacuum;
        private int number;
        private String type;        // "merlin"
        private String version;
        private String layout;
        private int engineLossMax;
        private String propellant1;
        private String propellant2;
        private int thrustToWeight;
    }

    @Data
    public static class LandingLegs {
        private int number;
        private String material; // nullable
    }

    @Data
    public static class PayloadWeight {
        private String id;   // "leo"
        private String name; // "Low Earth Orbit"
        private int kg;
        private int lb;
    }
}

