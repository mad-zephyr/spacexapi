package com.nx.spacex.controller;

import com.nx.spacex.dto.LaunchDto;
import com.nx.spacex.dto.RocketDto;
import com.nx.spacex.facade.Facade;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.nonNull;

@RestController
@AllArgsConstructor
@RequestMapping("/launches")
public class LaunchesController {

    private final Facade facade;

    @GetMapping
    public ResponseEntity<List<LaunchDto>> getLaunches(@RequestParam(required = false) String sort,
                                                       @RequestParam(required = false) Boolean success,
                                                       @RequestParam(required = false) Boolean upcoming,
                                                       @RequestParam(required = false) Boolean withRocket,
                                                       @RequestParam(required = false) Integer launch_year) {

        final Facade.GetLaunchesParamsDto searchParams = Facade.GetLaunchesParamsDto
                .builder()
                .sort(sort)
                .success(success)
                .upcoming(upcoming)
                .withRocket(withRocket)
                .launch_year(launch_year)
                .build();

        final List<LaunchDto> launches = facade.getLaunches(searchParams);

        return ResponseEntity.ok(launches);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaunchDto> getLaunch(@PathVariable String id, @RequestParam(required = false) Boolean withRocket) {
        final LaunchDto launch = facade.getLaunch(id);

        if (nonNull(launch)) {
            if (withRocket) {
                final String rocketId = launch.getRocket();
                final RocketDto rocket = facade.getRocket(rocketId);

                if (nonNull(rocket)) {
                    launch.setRocketData(rocket);
                }
            }

            return ResponseEntity.ok(launch);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
