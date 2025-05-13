package com.hydrolink.api.monitoring.bdd;

import com.hydrolink.api.auth.facade.AuthenticationFacade;
import com.hydrolink.api.auth.model.entities.UserEntity;
import com.hydrolink.api.auth.repository.UserRepository;
import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import static org.mockito.BDDMockito.given;

@CucumberContextConfiguration
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class CucumberTestContextConfiguration {

    @MockBean
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private UserRepository userRepository;   // <-- lo inyectamos

    private static boolean seeded = false;   // Ejecutar una sola vez

    @Before
    public void setupCucumberSpringContext() {

        if (!seeded) {               // ¡evitamos replicar usuarios!
            // 1. Creamos y guardamos un usuario real en la BD H2
            UserEntity persisted = userRepository.save(
                    UserEntity.builder()
                            .username("bdd_user")
                            .fullName("BDD User")
                            .password("noop")        // no importa para la prueba
                            .build());

            // 2. El mock siempre devolverá *esa* instancia
            given(authenticationFacade.getCurrentUser()).willReturn(persisted);

            seeded = true;
        }
    }
}
