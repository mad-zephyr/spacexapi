package com.nx.spacex.controller;

import com.nx.spacex.dto.LaunchDto;
import com.nx.spacex.facade.Facade;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/launches")
public class LaunchesController {

    private final Facade facade;


    @GetMapping
    public ResponseEntity<List<LaunchDto>> getLaunches(@RequestParam(required = false) String rocketName,
                                                       @RequestParam(required = false) String sort,
                                                       @RequestParam(required = false) Boolean success,
                                                       @RequestParam(required = false) Boolean upcoming,
                                                       @RequestParam(required = false) Integer launch_year) {

        final Facade.GetLaunchesParamsDto searchParams = Facade.GetLaunchesParamsDto
                .builder()
                .rocketName(rocketName)
                .success(success)
                .upcoming(upcoming)
                .sort(sort)
                .launch_year(launch_year)
                .build();

        final List<LaunchDto> launches = facade.getLaunches(searchParams);

        return ResponseEntity.ok(launches);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaunchDto> getLaunch(@PathVariable String id) {
        final Optional<LaunchDto> launch = facade.getLaunch(id);

        return launch
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

}
