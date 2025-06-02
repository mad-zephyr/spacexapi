package com.nx.spacex.service;

import com.nx.spacex.dto.LaunchDto;
import com.nx.spacex.utils.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.nx.spacex.utils.AppConstants.API_VERSION;
import static com.nx.spacex.utils.AppConstants.PROXY_BASE_URL;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class LaunchServiceImp implements LaunchService {

    private static final String BASE_URL = PROXY_BASE_URL + API_VERSION + "/launches";

    private final RestTemplate restTemplate;

    @Override
    public List<LaunchDto> getAll() {
        final LaunchDto[] launches = restTemplate.getForObject(BASE_URL, LaunchDto[].class);

        if (nonNull(launches) && launches.length > 0) {
            return List.of(launches);
        }

        return List.of();
    }

    @Override
    public Optional<LaunchDto> getById(String id) {
        final String url = BASE_URL + "/"+ id;

        try {
            final LaunchDto launchDto = restTemplate.getForObject(url, LaunchDto.class);
            return Optional.ofNullable(launchDto);
        } catch (RuntimeException e) {
            return Optional.empty();
        }

    }
}
