package com.nx.spacex.service;

import com.nx.spacex.dto.RocketDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static com.nx.spacex.utils.AppConstants.API_VERSION;
import static com.nx.spacex.utils.AppConstants.PROXY_BASE_URL;
import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class RocketsServiceImp implements RocketsService {

    private static final String BASE_URL = PROXY_BASE_URL + API_VERSION + "/rockets";

    private final RestTemplate restTemplate;

    public Optional<List<RocketDto>> getAll() {
        System.out.println("ALL: "+ BASE_URL);

        final RocketDto[] rocketsDto = restTemplate.getForObject(BASE_URL, RocketDto[].class);

        if (isNull(rocketsDto) || rocketsDto.length == 0) {
            return Optional.empty();
        }

        return Optional.of(List.of(rocketsDto));
    }

    public Optional<RocketDto> getById(String id) {

        final RocketDto rocketDto = restTemplate.getForObject(BASE_URL + "/" + id, RocketDto.class);

        if (isNull(rocketDto)) {
            return Optional.empty();
        }

        return Optional.of(rocketDto);
    }

}
