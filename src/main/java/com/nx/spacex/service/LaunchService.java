package com.nx.spacex.service;

import com.nx.spacex.dto.LaunchDto;

import java.util.List;
import java.util.Optional;

public interface LaunchService {

    List<LaunchDto> getAll();

    Optional<LaunchDto> getById(String id);

}
