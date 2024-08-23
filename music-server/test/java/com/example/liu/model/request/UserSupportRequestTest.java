package com.example.liu.model.request;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserSupportRequestTest {

    @Test
    public void testUserSupportRequest_WithValidInput() {
        UserSupportRequest request = new UserSupportRequest();
        request.setId(1);
        request.setCommentId(100);
        request.setUserId(200);

        assertThat(request.getId()).isEqualTo(1);
        assertThat(request.getCommentId()).isEqualTo(100);
        assertThat(request.getUserId()).isEqualTo(200);
    }

    @Test
    public void testUserSupportRequest_WithNullValues() {
        UserSupportRequest request = new UserSupportRequest();

        assertThat(request.getId()).isNull();
        assertThat(request.getCommentId()).isNull();
        assertThat(request.getUserId()).isNull();
    }
}
