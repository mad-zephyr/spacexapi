package com.nx.spacex.service;

import com.nx.spacex.dto.RocketDto;

import java.util.List;
import java.util.Optional;

public interface RocketsService {

    Optional<List<RocketDto>> getAll();

    Optional<RocketDto> getById(String id);
}
