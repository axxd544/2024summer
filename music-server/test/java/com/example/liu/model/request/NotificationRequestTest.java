package com.example.liu.model.request;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NotificationRequestTest {

    @Test
    public void testNotificationRequest_WithValidInput() {
        NotificationRequest request = new NotificationRequest();
        request.setUserId(1);
        request.setUserType("Admin");
        request.setMessage("This is a test message.");
        request.setType(2);

        assertThat(request.getUserId()).isEqualTo(1);
        assertThat(request.getUserType()).isEqualTo("Admin");
        assertThat(request.getMessage()).isEqualTo("This is a test message.");
        assertThat(request.getType()).isEqualTo(2);
    }

    @Test
    public void testNotificationRequest_WithNullValues() {
        NotificationRequest request = new NotificationRequest();

        assertThat(request.getUserId()).isNull();
        assertThat(request.getUserType()).isNull();
        assertThat(request.getMessage()).isNull();
        assertThat(request.getType()).isNull();
    }

    @Test
    public void testNotificationRequest_UpdateFields() {
        NotificationRequest request = new NotificationRequest();
        request.setUserId(1);
        request.setUserType("User");
        request.setMessage("Initial message.");
        request.setType(1);

        assertThat(request.getUserId()).isEqualTo(1);
        assertThat(request.getUserType()).isEqualTo("User");
        assertThat(request.getMessage()).isEqualTo("Initial message.");
        assertThat(request.getType()).isEqualTo(1);

        request.setUserId(2);
        request.setUserType("Admin");
        request.setMessage("Updated message.");
        request.setType(2);

        assertThat(request.getUserId()).isEqualTo(2);
        assertThat(request.getUserType()).isEqualTo("Admin");
        assertThat(request.getMessage()).isEqualTo("Updated message.");
        assertThat(request.getType()).isEqualTo(2);
    }
}
