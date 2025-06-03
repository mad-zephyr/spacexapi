package com.nx.spacex.mapper;


import com.nx.spacex.dto.LaunchDto;
import com.nx.spacex.entity.LaunchEntity;
import com.nx.spacex.entity.LinksEntity;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface LaunchMapper {

    LaunchDto toDto(LaunchEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "originalId", source = "id")
    @Mapping(target = "links", source =".", qualifiedByName = "LinksEntity")
    LaunchEntity toEntity(LaunchDto dto);

    @AfterMapping
    default void populateWithUUID(@MappingTarget LaunchEntity entity) {
        entity.setId(UUID.randomUUID());
    }

    @Named("LinksEntity")
    default LinksEntity linksToEntity(LaunchDto dto) {

        LaunchDto.LinksDto linksDto = dto.getLinks();
        LinksEntity linksEntity = new LinksEntity();

        linksEntity.setLinksId(UUID.randomUUID());
        linksEntity.setArticle(linksDto.getArticle());
        linksEntity.setPresskit(linksDto.getPresskit());
        linksEntity.setWebcast(linksDto.getWebcast());

        return linksEntity;
    }


//    @BeforeMapping
//    default void beforeSaveSetLaunchId(@MappingTarget LaunchEntity entity) {
//        entity.setId(UUID.randomUUID());
//    }
}
