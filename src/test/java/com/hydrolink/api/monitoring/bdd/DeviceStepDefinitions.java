package com.hydrolink.api.monitoring.bdd;

import com.hydrolink.api.auth.model.entities.UserEntity;
import com.hydrolink.api.auth.repository.UserRepository;
import com.hydrolink.api.monitoring.model.entities.Device;
import com.hydrolink.api.monitoring.model.enums.DeviceStatus;
import com.hydrolink.api.monitoring.repository.DeviceRepository;
import com.hydrolink.api.monitoring.services.DeviceService;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DeviceStepDefinitions {

    @Autowired private DeviceRepository deviceRepository;
    @Autowired private DeviceService   deviceService;
    @Autowired private UserRepository  userRepository;

    private Device     device;
    private UserEntity currentUser;   // ← usuario simulado por AuthenticationFacade

    /* ---------- Hooks ---------- */

    @Before
    public void cleanTables() {
        // ¡no borres usuarios!  el “bdd_user” debe seguir vivo
        deviceRepository.deleteAll();
        currentUser = userRepository.findUserEntityByUsername("bdd_user")
                .orElseThrow();
    }

    /* ---------- Given ---------- */

    @Given("un dispositivo con dirección MAC {string}")
    public void givenDeviceWithMac(String mac) {
        device = Device.create(mac, null);      // sin usuario
        deviceRepository.save(device);
    }

    /* ---------- When ---------- */

    @When("el usuario solicita conectar el dispositivo")
    public void whenUserConnectsDevice() {
        deviceService.connectDevice(device.getId());
    }

    /* ---------- Then ---------- */

    @Then("el dispositivo se marca como ACTIVO")
    public void thenDeviceIsActive() {
        assertEquals(DeviceStatus.ACTIVE, refreshed().getStatus());
    }

    @Then("se asigna correctamente al usuario")
    public void thenDeviceIsAssigned() {
        assertEquals(currentUser.getId(), refreshed().getUser().getId());
    }

    @Then("el dispositivo contiene {int} sensores predefinidos")
    public void thenDeviceHasNSensors(int expected) {
        assertEquals(expected, refreshed().getSensors().size());
    }

    /* ---------- helper ---------- */

    private Device refreshed() {
        return deviceRepository.findById(device.getId()).orElseThrow();
    }
}
