package com.hydrolink.api.monitoring.repository;

import com.hydrolink.api.monitoring.model.entities.Sensor;
import com.hydrolink.api.monitoring.model.enums.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {

    Optional<Sensor> findByTypeAndDeviceId(SensorType type, Long deviceDataId);

    List<Sensor> findAllByDeviceId(Long deviceDataId);
}
