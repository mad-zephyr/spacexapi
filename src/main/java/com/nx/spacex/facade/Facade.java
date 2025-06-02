package com.nx.spacex.facade;

import com.nx.spacex.dto.LaunchDto;
import com.nx.spacex.dto.RocketDto;
import com.nx.spacex.service.LaunchService;
import com.nx.spacex.service.RocketsService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class Facade {

    private final LaunchService launchService;
    private final RocketsService rocketsService;

    public List<LaunchDto> getLaunches(GetLaunchesParamsDto params) {
        final List<LaunchDto> launches = getFiltered(params, launchService.getAll());

        if (nonNull(launches) && params.withRocket) {
            final Optional<List<RocketDto>> optionalRockets = rocketsService.getAll();

            if (optionalRockets.isPresent()) {
                final List<RocketDto> rockets = optionalRockets.get();
                final HashMap<String, RocketDto> rocketMap = new HashMap<>();

                rockets.forEach(rocket -> rocketMap.put(rocket.getId(), rocket));

                return launches.stream()
                        .peek(launch -> launch.setRocketData(rocketMap.get(launch.getRocket())))
                        .toList();
            }

            return launches;
        }

        return launches;
    }

    public LaunchDto getLaunch(String launchId) {
        Optional<LaunchDto> launchDto = launchService.getById(launchId);

        return launchDto.orElseGet(() -> null);

    }

    public List<RocketDto> getRockets() {
        final Optional<List<RocketDto>> rocketDtoList = rocketsService.getAll();

        return rocketDtoList.orElseGet(List::of);
    }

    public RocketDto getRocket(String id) {
        return rocketsService.getById(id).orElse(null);
    }

    private List<LaunchDto> getFiltered(GetLaunchesParamsDto params, List<LaunchDto> launches) {

        if (isNull(launches)) {
            return List.of();
        }


        return launches.stream()
                .filter(launch -> isNull(params.success) || launch.isSuccess() == params.success)
                .filter(launch -> isNull(params.launch_year) && nonNull(launch.getDateUtc()))
                .filter(launch -> isNull(params.upcoming) || Boolean.compare(params.upcoming, launch.isUpcoming()) == 0)
                .sorted((launchA, launchB) -> {
                    if (isNull(params.sort)) {
                        return 1;
                    }

                    return params.sort.equals("asc")
                            ? launchA.getDateUtc().compareTo(launchB.getDateUtc())
                            : launchB.getDateUtc().compareTo(launchA.getDateUtc());
                })
                .toList();
    }

    @Data
    @Builder
    public static class GetLaunchesParamsDto {
        String sort;
        Boolean success;
        Boolean upcoming;
        Boolean withRocket;
        Integer launch_year;
    }
}
