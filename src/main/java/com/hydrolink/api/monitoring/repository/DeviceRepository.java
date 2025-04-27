package com.hydrolink.api.monitoring.repository;

import com.hydrolink.api.monitoring.model.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    Optional<Device> findByUserId(Long userId);

    Optional<Device> findByMacAddress(String macAddress);
}
