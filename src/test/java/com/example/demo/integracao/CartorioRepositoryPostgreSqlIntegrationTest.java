package com.example.demo.integracao;

import com.example.demo.cartorios.model.Cartorio;
import com.example.demo.cartorios.model.Documento;
import com.example.demo.cartorios.repository.CartorioRepository;
import com.example.demo.cartorios.repository.DocumentoRepository;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.transaction.Transactional;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = {CartorioRepositoryPostgreSqlIntegrationTest.Initializer.class})
@Profile("pgtest")
@ActiveProfiles(profiles = "pgtest")
public class CartorioRepositoryPostgreSqlIntegrationTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = (PostgreSQLContainer) new PostgreSQLContainer("postgres:11.1")
            .withDatabaseName("integration-tests-db")
            .withUsername("sa")
            .withPassword("sa")
            .withInitScript("schema-pgtest.sql");

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Autowired
    private CartorioRepository cartorioRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Test
    @Transactional
    public void salvarCartorio() {

        Documento documento = new Documento("nome");

        documentoRepository.save(documento);

        Cartorio cartorio = cartorioRepository.save(new Cartorio("nome", "email", Arrays.asList(documento)));

        Long cartorioId = cartorio.getId();

        Assert.assertNotNull(cartorioId);

        cartorio = cartorioRepository.findById(cartorioId).orElseThrow(RuntimeException::new);

        Assert.assertTrue(!cartorio.getDocumentoList().isEmpty());
    }


}