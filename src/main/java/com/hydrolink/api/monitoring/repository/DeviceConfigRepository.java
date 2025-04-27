package com.hydrolink.api.monitoring.repository;

import com.hydrolink.api.monitoring.model.entities.DeviceConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceConfigRepository extends JpaRepository<DeviceConfig, Long> {
//    Optional<DeviceConfig> findByUserId(Long userId);

    Optional<DeviceConfig> findByDeviceId(Long id);
}
