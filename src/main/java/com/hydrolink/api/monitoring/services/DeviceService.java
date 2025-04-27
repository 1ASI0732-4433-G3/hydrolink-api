package com.hydrolink.api.monitoring.services;

import com.hydrolink.api.auth.facade.AuthenticationFacade;
import com.hydrolink.api.auth.model.entities.UserEntity;
import com.hydrolink.api.monitoring.model.entities.*;
import com.hydrolink.api.monitoring.model.entities.Device;
import com.hydrolink.api.monitoring.model.enums.DeviceStatus;
import com.hydrolink.api.monitoring.repository.DeviceConfigRepository;
import com.hydrolink.api.monitoring.repository.DeviceRepository;
import com.hydrolink.api.monitoring.repository.MetricRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceConfigRepository deviceConfigRepository;
    private final MetricRepository metricRepository;
    private final AuthenticationFacade authenticationFacade;

    public void connectDevice(Long id) {

        UserEntity user = authenticationFacade.getCurrentUser();

        Device device = getDeviceById(id);
        device.setUser(user);
        device.setStatus(DeviceStatus.ACTIVE);

        deviceRepository.save(device);
    }

    public Device getDeviceById(Long id) {

        return deviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Device not found"));
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }
}
