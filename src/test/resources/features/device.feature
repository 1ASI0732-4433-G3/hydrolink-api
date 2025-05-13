Feature: Conexión de dispositivo al usuario autenticado

  Scenario: Usuario conecta un nuevo dispositivo a su cuenta
    And un dispositivo con dirección MAC "AA:BB:CC:DD:EE:99"
    When el usuario solicita conectar el dispositivo
    Then el dispositivo se marca como ACTIVO
    And se asigna correctamente al usuario
    And el dispositivo contiene 0 sensores predefinidos
