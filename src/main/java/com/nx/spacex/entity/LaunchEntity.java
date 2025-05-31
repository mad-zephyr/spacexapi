package com.nx.spacex.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.UUID;

@Entity
@Setter
@Getter
@ToString
@Table(name = "launch")
public class LaunchEntity {
    @Id
    @Column(columnDefinition = "uuid",  nullable = false)
    private UUID id;
    private String originalId;

    private String name;
    private Instant staticFireDateUtc;
    private Instant dateUtc;

    @Column(nullable = true)
    private boolean success;

    @Column(nullable = true)
    private String rocket;

    @Column(nullable = true)
    private boolean upcoming;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "links_id", referencedColumnName = "id")
    private LinksEntity links;

//    private long staticFireDateUnix;
//    private boolean net;
//    @Column(name = "launch_window")
//    private int window;
//    private boolean success;
//    private int flightNumber;
//    private String dateLocal;
//    private String datePrecision;
//    private long dateUnix;
//    private boolean upcoming;
//    private boolean autoUpdate;
//    private boolean tbd;
//    private String launchLibraryId;

//    private String rocket;
//    private String details;
//    private String launchpad;
//    private List<String> crew;
//    private List<String> ships;
//    private List<String> capsules;
//    private List<String> payloads;
//    private LaunchDto.FairingsDto fairings;
//    private LaunchDto.LinksDto links;
//    private List<LaunchDto.FailureDto> failures;
//    private List<LaunchDto.CoreDto> cores;

}
