package com.example.liu.model.request;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsumerRequestTest {

    @Test
    public void testConsumerRequest_WithValidInput() {
        Date currentDate = new Date();

        ConsumerRequest request = new ConsumerRequest();
        request.setId(1);
        request.setUsername("testUser");
        request.setOldPassword("oldPassword123");
        request.setPassword("newPassword123");
        request.setSex((byte) 1);
        request.setPhoneNum("1234567890");
        request.setEmail("test@example.com");
        request.setBirth(currentDate);
        request.setIntroduction("This is a test user.");
        request.setLocation("Test Location");
        request.setAvator("testAvator.png");
        request.setCreateTime(currentDate);

        assertThat(request.getId()).isEqualTo(1);
        assertThat(request.getUsername()).isEqualTo("testUser");
        assertThat(request.getOldPassword()).isEqualTo("oldPassword123");
        assertThat(request.getPassword()).isEqualTo("newPassword123");
        assertThat(request.getSex()).isEqualTo((byte) 1);
        assertThat(request.getPhoneNum()).isEqualTo("1234567890");
        assertThat(request.getEmail()).isEqualTo("test@example.com");
        assertThat(request.getBirth()).isEqualTo(currentDate);
        assertThat(request.getIntroduction()).isEqualTo("This is a test user.");
        assertThat(request.getLocation()).isEqualTo("Test Location");
        assertThat(request.getAvator()).isEqualTo("testAvator.png");
        assertThat(request.getCreateTime()).isEqualTo(currentDate);
    }

    @Test
    public void testConsumerRequest_WithNullValues() {
        ConsumerRequest request = new ConsumerRequest();

        assertThat(request.getId()).isNull();
        assertThat(request.getUsername()).isNull();
        assertThat(request.getOldPassword()).isNull();
        assertThat(request.getPassword()).isNull();
        assertThat(request.getSex()).isNull();
        assertThat(request.getPhoneNum()).isNull();
        assertThat(request.getEmail()).isNull();
        assertThat(request.getBirth()).isNull();
        assertThat(request.getIntroduction()).isNull();
        assertThat(request.getLocation()).isNull();
        assertThat(request.getAvator()).isNull();
        assertThat(request.getCreateTime()).isNull();
    }

    @Test
    public void testConsumerRequest_UpdateField() {
        Date initialDate = new Date();
        Date updatedDate = new Date(initialDate.getTime() + 10000);

        ConsumerRequest request = new ConsumerRequest();
        request.setCreateTime(initialDate);

        assertThat(request.getCreateTime()).isEqualTo(initialDate);

        request.setCreateTime(updatedDate);

        assertThat(request.getCreateTime()).isEqualTo(updatedDate);
    }
}
