package com.hydrolink.api.monitoring.repository;

import com.hydrolink.api.monitoring.model.entities.SensorConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorConfigRepository extends JpaRepository<SensorConfig, Long> {

}
