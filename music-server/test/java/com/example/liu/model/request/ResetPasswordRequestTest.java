package com.example.liu.model.request;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResetPasswordRequestTest {

    @Test
    public void testResetPasswordRequest_WithValidInput() {
        ResetPasswordRequest request = new ResetPasswordRequest(
                "test@example.com", 
                "123456", 
                "newpassword", 
                "newpassword"
        );

        assertThat(request.getEmail()).isEqualTo("test@example.com");
        assertThat(request.getCode()).isEqualTo("123456");
        assertThat(request.getPassword()).isEqualTo("newpassword");
        assertThat(request.getConfirmPassword()).isEqualTo("newpassword");
    }

    @Test
    public void testResetPasswordRequest_WithDefaultValues() {
        ResetPasswordRequest request = new ResetPasswordRequest();

        assertThat(request.getEmail()).isNull();
        assertThat(request.getCode()).isNull();
        assertThat(request.getPassword()).isNull();
        assertThat(request.getConfirmPassword()).isNull();
    }

    @Test
    public void testResetPasswordRequest_UpdateFields() {
        ResetPasswordRequest request = new ResetPasswordRequest(
                "test@example.com", 
                "123456", 
                "password1", 
                "password1"
        );

        assertThat(request.getPassword()).isEqualTo("password1");

        request.setPassword("password2");
        request.setConfirmPassword("password2");

        assertThat(request.getPassword()).isEqualTo("password2");
        assertThat(request.getConfirmPassword()).isEqualTo("password2");
    }
}
