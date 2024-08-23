package com.example.liu.model.request;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class SingerRequestTest {

    @Test
    public void testSingerRequest_WithValidInput() {
        Date currentDate = new Date();

        SingerRequest request = new SingerRequest();
        request.setId(1);
        request.setName("John Doe");
        request.setSex((byte) 1);
        request.setPic("pic_url");
        request.setBirth(currentDate);
        request.setLocation("New York");
        request.setIntroduction("A popular singer.");

        assertThat(request.getId()).isEqualTo(1);
        assertThat(request.getName()).isEqualTo("John Doe");
        assertThat(request.getSex()).isEqualTo((byte) 1);
        assertThat(request.getPic()).isEqualTo("pic_url");
        assertThat(request.getBirth()).isEqualTo(currentDate);
        assertThat(request.getLocation()).isEqualTo("New York");
        assertThat(request.getIntroduction()).isEqualTo("A popular singer.");
    }

    @Test
    public void testSingerRequest_WithNullValues() {
        SingerRequest request = new SingerRequest();

        assertThat(request.getId()).isNull();
        assertThat(request.getName()).isNull();
        assertThat(request.getSex()).isNull();
        assertThat(request.getPic()).isNull();
        assertThat(request.getBirth()).isNull();
        assertThat(request.getLocation()).isNull();
        assertThat(request.getIntroduction()).isNull();
    }

    @Test
    public void testSingerRequest_UpdateFields() {
        Date initialDate = new Date();
        Date updatedDate = new Date(initialDate.getTime() + 10000);

        SingerRequest request = new SingerRequest();
        request.setBirth(initialDate);
        assertThat(request.getBirth()).isEqualTo(initialDate);

        request.setBirth(updatedDate);
        assertThat(request.getBirth()).isEqualTo(updatedDate);
    }
}
