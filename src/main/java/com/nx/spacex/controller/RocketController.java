package com.nx.spacex.controller;

import com.nx.spacex.dto.RocketDto;
import com.nx.spacex.facade.Facade;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
@AllArgsConstructor
@RequestMapping("/rockets")
public class RocketController {

    private final Facade facade;

    @GetMapping
    public ResponseEntity<List<RocketDto>> getRockets() {
        final List<RocketDto> rockets = facade.getRockets();

        return ResponseEntity.ok(rockets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RocketDto> getRocketById(@PathVariable String id) {
        final RocketDto rocket = facade.getRocket(id);

        if (isNull(rocket)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(rocket);
    }
}
