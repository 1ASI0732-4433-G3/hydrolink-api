package com.hydrolink.api.monitoring.model.entities;

import com.hydrolink.api.monitoring.model.enums.DeviceStatus;
import com.hydrolink.api.monitoring.model.enums.SensorType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SensorTest {

    @Test
    void shouldAddMetricToSensor(){

        //Arrange
        Sensor sensor = Sensor.create(SensorType.HUMIDITY, null);
        Metric metric = Metric.create(10.0, "g/m3", sensor);

        //Act
       sensor.addMetric(metric);

        //Assert
        assertEquals(sensor.getMetrics().getFirst(), metric);

    }
    @Test
    void shouldRemoveMetricFromSensor(){

        //Arrange
        Sensor sensor = Sensor.create(SensorType.HUMIDITY, null);
        Metric metric = Metric.create(10.0, "g/m3", sensor);

        //Act
        sensor.addMetric(metric);
        sensor.removeMetric(metric);

        //Assert
        assertEquals(sensor.getMetrics().size(), 0);

    }
    @Test
    void shouldCreateSensorWithDefaultStatusAndDevice() {
        // Arrange
        Device device = Device.create("AA:BB:CC:DD:EE:FF", null);

        // Act
        Sensor sensor = Sensor.create(SensorType.TEMPERATURE, device);

        // Assert
        assertEquals(SensorType.TEMPERATURE, sensor.getType());
        assertEquals(DeviceStatus.ACTIVE, sensor.getStatus());
        assertEquals(device, sensor.getDevice());
    }


}
