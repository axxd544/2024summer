package com.example.liu.model.request;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FollowRequestTest {

    @Test
    public void testFollowRequest_WithValidInput() {
        FollowRequest request = new FollowRequest();
        request.setId(1);
        request.setFollowerId(2);
        request.setFollowedId(3);

        assertThat(request.getId()).isEqualTo(1);
        assertThat(request.getFollowerId()).isEqualTo(2);
        assertThat(request.getFollowedId()).isEqualTo(3);
    }

    @Test
    public void testFollowRequest_WithNullValues() {
        FollowRequest request = new FollowRequest();

        assertThat(request.getId()).isNull();
        assertThat(request.getFollowerId()).isNull();
        assertThat(request.getFollowedId()).isNull();
    }

    @Test
    public void testFollowRequest_UpdateField() {
        FollowRequest request = new FollowRequest();
        request.setFollowerId(2);
        request.setFollowedId(3);

        assertThat(request.getFollowerId()).isEqualTo(2);
        assertThat(request.getFollowedId()).isEqualTo(3);

        request.setFollowerId(4);
        request.setFollowedId(5);

        assertThat(request.getFollowerId()).isEqualTo(4);
        assertThat(request.getFollowedId()).isEqualTo(5);
    }
}
