package com.nx.spacex.repository;


import com.nx.spacex.entity.LaunchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LaunchRepository extends JpaRepository<LaunchEntity, UUID> {}
