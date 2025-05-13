package com.hydrolink.api.monitoring.model.entities;

import com.hydrolink.api.monitoring.model.enums.DeviceStatus;
import org.testng.annotations.Test;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeviceTest {


    @Test
    void shouldCreateDeviceWithDefaults() {
        // Arrange
        String mac = "11:22:33:44:55:66";

        // Act
        Device device = Device.create(mac, null);

        // Assert
        assertEquals(mac, device.getMacAddress());
        assertEquals("Living Room", device.getLocation());
        assertEquals(DeviceStatus.INACTIVE, device.getStatus());
        assertNotNull(device.getSensors());
        assertEquals(0, device.getSensors().size());
    }


}
