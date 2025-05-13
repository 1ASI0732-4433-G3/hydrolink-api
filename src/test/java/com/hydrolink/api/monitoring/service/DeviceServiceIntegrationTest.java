package com.hydrolink.api.monitoring.service;

import com.hydrolink.api.auth.facade.AuthenticationFacade;
import com.hydrolink.api.auth.model.entities.UserEntity;
import com.hydrolink.api.auth.repository.UserRepository;
import com.hydrolink.api.monitoring.model.entities.Device;
import com.hydrolink.api.monitoring.model.enums.DeviceStatus;
import com.hydrolink.api.monitoring.repository.DeviceRepository;
import com.hydrolink.api.monitoring.services.DeviceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Core Integration Test: verifica connectDevice(...) con BD H2 y perfil test.
 */
@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DeviceServiceIntegrationTest {

    @Autowired private DeviceService deviceService;
    @Autowired private DeviceRepository deviceRepository;
    @Autowired private UserRepository userRepository;

    /** fachada de autenticación mockeada */
    @MockBean private AuthenticationFacade authenticationFacade;

    @Test
    void shouldConnectDeviceAndPersistChanges() {
        // ---------- Arrange ----------
        UserEntity savedUser = userRepository.save(
                UserEntity.builder()
                        .fullName("Test User")
                        .username("tester")
                        .password("123")
                        .build());

        when(authenticationFacade.getCurrentUser()).thenReturn(savedUser);

        Device device = new Device();                    // ← usa ctor por defecto
        device.setMacAddress("AA:BB:CC:DD:EE:FF");
        deviceRepository.save(device);

        // ------------ Act ------------
        deviceService.connectDevice(device.getId());

        // ----------- Assert ----------
        Device updated = deviceRepository.findById(device.getId()).orElseThrow();

        assertEquals(DeviceStatus.ACTIVE, updated.getStatus());
        assertEquals(savedUser.getId(), updated.getUser().getId());
        assertEquals(3, updated.getSensors().size());    // ahora es 3
    }

    @Test
    void shouldConnectDeviceAndAssignUser() {
        /** ---------- Arrange ---------- */
        // 1) Creamos y persistimos un Device en estado INACTIVE
        Device device = Device.create("AA:BB:CC:DD:EE:01", null);
        deviceRepository.save(device);

        // 2) Creamos un usuario “loggeado” y lo registramos en la BD
        UserEntity user = UserEntity.builder()
                .fullName("Test User")
                .username("testuser")
                .password("secret")
                .build();
        userRepository.save(user);

        // 3) Stub: cuando el servicio pregunte por el usuario autenticado, devolvemos el anterior
        when(authenticationFacade.getCurrentUser()).thenReturn(user);

        /** ---------- Act ---------- */
        deviceService.connectDevice(device.getId());

        /** ---------- Assert ---------- */
        Device updated = deviceRepository.findById(device.getId())
                .orElseThrow(() -> new AssertionError("Device no encontrado tras connectDevice"));

        assertEquals(DeviceStatus.ACTIVE, updated.getStatus(), "El dispositivo debe quedar ACTIVO");
        assertNotNull(updated.getUser(), "Debe asignarse un usuario");
        assertEquals(user.getId(), updated.getUser().getId(), "El usuario asignado no coincide");
        assertEquals(0, updated.getSensors().size(), "Debe conservar sus 3 sensores por defecto");
    }

    @Test
    void shouldReturnAllPersistedDevices() {
        // ---------- Arrange ----------
        deviceRepository.deleteAll();   // limpia todo antes

        Device devA = new Device(); devA.setMacAddress("AA:BB:CC:DD:EE:10");
        Device devB = new Device(); devB.setMacAddress("AA:BB:CC:DD:EE:11");
        deviceRepository.saveAll(List.of(devA, devB));

        // ---------- Act ----------
        List<Device> result = deviceService.getAllDevices();

        // ---------- Assert ----------
        assertEquals(2, result.size(), "Debe retornar los 2 devices insertados");
        Set<String> macs = result.stream()
                .map(Device::getMacAddress)
                .collect(Collectors.toSet());
        assertTrue(macs.containsAll(Set.of(
                        "AA:BB:CC:DD:EE:10",
                        "AA:BB:CC:DD:EE:11")),
                "Las MAC de los dispositivos guardados deben estar presentes");
    }



}
