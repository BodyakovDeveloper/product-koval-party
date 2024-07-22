package com.koval.shop.security.service;

import com.koval.shop.payload.request.SigninRequest;
import com.koval.shop.payload.response.JwtAuthenticationResponse;

public interface AuthenticationService {

    JwtAuthenticationResponse signin(SigninRequest request);
}