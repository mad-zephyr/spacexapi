package com.nx.spacex.facade;

import com.nx.spacex.dto.LaunchDto;
import com.nx.spacex.service.LaunchService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class Facade {

    private final LaunchService launchService;

    public List<LaunchDto> getLaunches(GetLaunchesParamsDto params) {
        return getFiltered(params, launchService.getAll());
    }

    public Optional<LaunchDto> getLaunch(String launchId) {
        return launchService.getById(launchId);
    }

    private List<LaunchDto> getFiltered(GetLaunchesParamsDto params, List<LaunchDto> launches) {

        if (isNull(launches)) {
            return List.of();
        }


        return launches.stream()
                .filter(launch -> isNull(params.success) || launch.isSuccess() == params.success)
                .filter(launch -> isNull(params.launch_year) && nonNull(launch.getDateUtc()))
                .filter(launch -> isNull(params.rocketName) || params.rocketName.equals(launch.getRocket()))
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
        String rocketName;
        String sort;
        Boolean success;
        Boolean upcoming;
        Integer launch_year;
    }
}
