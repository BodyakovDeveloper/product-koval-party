package com.koval.shop.controller;

import com.koval.shop.payload.request.SigninRequest;
import com.koval.shop.payload.response.JwtAuthenticationResponse;
import com.koval.shop.security.service.AuthenticationService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Handles user signin.
     *
     * @param request the signin request
     * @return a response entity containing the JWT authentication response
     */
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@Valid @RequestBody SigninRequest request) {
        log.debug("AuthController starts proceed signin with request={}", request);
        JwtAuthenticationResponse signin = authenticationService.signin(request);
        log.debug("AuthController ends, returning responseEntity={}", signin);
        return ResponseEntity.ok(signin);
    }
}
