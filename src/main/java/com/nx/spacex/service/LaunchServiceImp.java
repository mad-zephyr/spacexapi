package com.nx.spacex.service;

import com.nx.spacex.dto.LaunchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LaunchServiceImp implements LaunchService {
    private static final String BASE_URL = "https://api.spacexdata.com/v4/launches/";

    private final RestTemplate restTemplate;

    @Override
    public List<LaunchDto> getAll() {
        final LaunchDto[] launches = restTemplate.getForObject(BASE_URL, LaunchDto[].class);

        if (Objects.nonNull(launches) && launches.length > 0) {
            return List.of(launches);
        }

        return List.of();
    }

    @Override
    public Optional<LaunchDto> getById(String id) {
        final String url = BASE_URL + id;

        try {
            final LaunchDto launchDto = restTemplate.getForObject(url, LaunchDto.class);
            return Optional.ofNullable(launchDto);
        } catch (RuntimeException e) {
            return Optional.empty();
        }

    }
}
