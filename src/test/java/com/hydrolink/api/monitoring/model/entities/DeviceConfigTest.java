package com.hydrolink.api.monitoring.model.entities;

import com.hydrolink.api.monitoring.model.enums.SensorType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeviceConfigTest {


    @Test
    void shouldInitializeDeviceConfigWithDefaultValues() {
        // Arrange
        Device device = Device.create("AA:BB:CC:DD:EE:FF", null);

        // Act
        DeviceConfig config = new DeviceConfig(device);

        // Assert
        assertEquals(10, config.getSampleInterval());
        assertEquals(15.0, config.getTempMin());
        assertEquals(30.0, config.getTempMax());
        assertEquals(28.0, config.getTempThreshold());

        assertEquals(40.0, config.getHumMin());
        assertEquals(60.0, config.getHumMax());
        assertEquals(55.0, config.getHumThreshold());

        assertEquals(300, config.getLumMin());
        assertEquals(1000, config.getLumMax());
        assertEquals(900, config.getLumThreshold());
    }


    @Test
    void shouldUpdateDeviceConfigurationCorrectly(){

        //Arrange
        Device device = Device.create("00:1A:2B:3C:4D:5E", null);
        DeviceConfig deviceConfig = new DeviceConfig(device);
        Sensor sensor = Sensor.create(SensorType.HUMIDITY, null);

        SensorConfig config = new SensorConfig(sensor);

        //Act
        deviceConfig.update(config);

        // Assert (solo humedad cambia)
        assertEquals(15.0, deviceConfig.getTempMin());
        assertEquals(30.0, deviceConfig.getTempMax());
        assertEquals(28.0, deviceConfig.getTempThreshold());

        assertEquals(40.0, deviceConfig.getHumMin());
        assertEquals(60.0, deviceConfig.getHumMax());
        assertEquals(55.0, deviceConfig.getHumThreshold());
    }
}
