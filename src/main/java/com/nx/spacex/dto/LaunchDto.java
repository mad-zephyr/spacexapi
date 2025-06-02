package com.nx.spacex.dto;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LaunchDto {
    private String id;
    private String name;
    private Instant dateUtc;
    private boolean success;
    private boolean upcoming;
    private LinksDto links;

    private String rocket;
    private RocketDto rocketData;

//
//    private long staticFireDateUnix;
//    private boolean net;
//    private int window;
//    private String details;
//    private List<String> crew;
//    private List<String> ships;
//    private List<String> capsules;
//    private List<String> payloads;
//    private String launchpad;
//    private int flightNumber;
//    private long dateUnix;
//    private boolean autoUpdate;
//    private boolean tbd;
//    private String dateLocal;
//    private String datePrecision;
//    private String launchLibraryId;

//    private List<CoreDto> cores;
//    private FairingsDto fairings;
//    private List<FailureDto> failures;

    @Data
    public static class FairingsDto {
        private boolean reused;
        private boolean recoveryAttempt;
        private boolean recovered;
        private List<String> ships;
    }

    @Data
    public static class LinksDto {
        private PatchDto patch;
        private RedditDto reddit;
        private FlickrDto flickr;
        private String presskit;
        private String webcast;
        private String youtubeId;
        private String article;
        private String wikipedia;
    }

    @Data
    public static class PatchDto {
        private String small;
        private String large;
    }

    @Data
    public static class RedditDto {
        private String campaign;
        private String launch;
        private String media;
        private String recovery;
    }

    @Data
    public static class FlickrDto {
        private List<String> small;
        private List<String> original;
    }

    @Data
    public static class FailureDto {
        private int time;
        private Integer altitude;
        private String reason;
    }

    @Data
    public static class CoreDto {
        private String core;
        private int flight;
        private boolean gridfins;
        private boolean legs;
        private boolean reused;
        private boolean landingAttempt;
        private Boolean landingSuccess;
        private String landingType;
        private String landpad;
    }
}

