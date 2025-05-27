package com.hydrolink.api.auth.handler;

import com.hydrolink.api.auth.model.entities.PermissionEntity;
import com.hydrolink.api.auth.model.entities.RoleEntity;
import com.hydrolink.api.auth.model.entities.UserEntity;
import com.hydrolink.api.auth.model.enums.RoleEnum;
import com.hydrolink.api.auth.repository.RoleRepository;
import com.hydrolink.api.auth.repository.UserRepository;
import com.hydrolink.api.monitoring.repository.DeviceConfigRepository;
import com.hydrolink.api.monitoring.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Set;

@Log4j2
@Service
@RequiredArgsConstructor
public class SeedingEventHandler {
    private final UserRepository userPersistence;
    private final RoleRepository rolePersistence;
    private final PasswordEncoder passwordEncoder;

    @EventListener
    public void on(ApplicationReadyEvent event) {
        var name = event.getApplicationContext().getId();
        log.info("Starting to seed roles and users for {} at {}", name, new Timestamp(System.currentTimeMillis()));

        PermissionEntity createPermission = PermissionEntity.builder().name("CREATE").build();
        PermissionEntity readPermission = PermissionEntity.builder().name("READ").build();
        PermissionEntity updatePermission = PermissionEntity.builder().name("UPDATE").build();
        PermissionEntity deletePermission = PermissionEntity.builder().name("DELETE").build();

        if (rolePersistence.findAll().isEmpty()) {

            RoleEntity roleAdmin = RoleEntity.builder()
                    .roleName(RoleEnum.ADMIN)
                    .permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
                    .build();

            RoleEntity roleUser = RoleEntity.builder()
                    .roleName(RoleEnum.AMATEUR)
                    .permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
                    .build();

            RoleEntity roleGuest = RoleEntity.builder()
                    .roleName(RoleEnum.EXPERT)
                    .permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
                    .build();

            rolePersistence.saveAll(Set.of(roleAdmin, roleUser, roleGuest));

            seedUsers(roleAdmin);

            log.info("Finished seeding roles and users for {} at {}", name, new Timestamp(System.currentTimeMillis()));
        }

    }

    private void seedUsers(RoleEntity roleAdmin) {

        UserEntity user = UserEntity.builder()
                .fullName("Admin User")
                .username("admin")
                .password(passwordEncoder.encode("123456"))
                .roles(Set.of(roleAdmin))
                .build();

        userPersistence.save(user);
    }
}