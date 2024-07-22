package com.koval.shop.controller;


import com.koval.shop.testdata.initializer.PostgresContainerInitializer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {PostgresContainerInitializer.class})
public class ControllerTestBase {

    @LocalServerPort
    private int localPort;

    protected String baseUrl = "http://localhost:" + localPort;
}