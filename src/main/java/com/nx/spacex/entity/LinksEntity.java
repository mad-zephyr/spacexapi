package com.nx.spacex.entity;


import jakarta.persistence.*;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Setter
@ToString
@Table(name = "links")
public class LinksEntity {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;
    private String presskit;
    private String webcast;
    private String youtubeId;
    private String article;
    private String wikipedia;

    private UUID linksId;
}