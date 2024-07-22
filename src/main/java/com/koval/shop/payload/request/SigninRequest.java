package com.koval.shop.payload.request;

import jakarta.validation.constraints.Pattern;

import static com.koval.shop.util.RegexConstant.PASSWORD_REGEX;
import static com.koval.shop.util.RegexConstant.USERNAME_REGEX;

public class SigninRequest {

    @Pattern(regexp = USERNAME_REGEX, message = "Incorrect username")
    private String username;

    @Pattern(regexp = PASSWORD_REGEX, message = "Incorrect password")
    private String password;

    public SigninRequest() {

    }

    public SigninRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public @Pattern(regexp = USERNAME_REGEX, message = "Incorrect username") String getUsername() {
        return username;
    }

    public void setUsername(@Pattern(regexp = USERNAME_REGEX, message = "Incorrect username") String username) {
        this.username = username;
    }

    public @Pattern(regexp = PASSWORD_REGEX, message = "Incorrect password") String getPassword() {
        return password;
    }

    public void setPassword(@Pattern(regexp = PASSWORD_REGEX, message = "Incorrect password") String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SigninRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}