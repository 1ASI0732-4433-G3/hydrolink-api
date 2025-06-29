package com.hydrolink.api.notification.controllers;

import com.hydrolink.api.auth.facade.AuthenticationFacade;
import com.hydrolink.api.notification.model.entities.Notification;
import com.hydrolink.api.notification.services.NotificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
@Tag(name = "Notification Controller", description = "API for notification operations")
public class NotificationController {

    private final NotificationService notificationService;
    private final AuthenticationFacade authenticationFacade;

    // Endpoint para obtener las notificaciones del usuario autenticado
    @GetMapping()
    public ResponseEntity<List<Notification>> getUserNotifications() {
        Long userId = authenticationFacade.getCurrentUser().getId();
        List<Notification> notifications = notificationService.getUserNotifications(userId);
        return ResponseEntity.ok(notifications);
    }

    // Endpoint para borrar todas las notificaciones del usuario autenticado
    @DeleteMapping()
    public ResponseEntity<Void> clearUserNotifications() {
        Long userId = authenticationFacade.getCurrentUser().getId();
        notificationService.clearUserNotifications(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
