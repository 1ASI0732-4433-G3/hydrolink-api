package com.hydrolink.api.monitoring.model.entities;

import com.hydrolink.api.monitoring.model.enums.SensorType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SensorConfigTest {

    @Test
    void shouldUpdateThresholdsCorrectly() {
        // Arrange
        SensorConfig config = SensorConfig.builder()
                .min(10.0)
                .max(30.0)
                .threshold(20.0)
                .build();

        // Act
        config.update(15.0, 35.0, 25.0);

        // Assert
        assertEquals(15.0, config.getMin());
        assertEquals(35.0, config.getMax());
        assertEquals(25.0, config.getThreshold());
    }
    @Test
    void shouldInitializeDefaultValuesForHumiditySensor(){

        //Arrange
        Sensor sensor = Sensor.create(SensorType.HUMIDITY, null);

        //Act
        SensorConfig config = new SensorConfig(sensor);

        //Assert
        assertEquals(40.0, config.getMin());
        assertEquals(60.0, config.getMax());
        assertEquals(55.0, config.getThreshold());
        assertEquals(sensor, config.getSensor());
    }
}
