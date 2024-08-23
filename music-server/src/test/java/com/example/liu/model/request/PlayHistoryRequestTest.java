package com.example.liu.model.request;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayHistoryRequestTest {

    @Test
    public void testPlayHistoryRequest_WithValidInput() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        PlayHistoryRequest request = new PlayHistoryRequest();
        request.setId(1);
        request.setUserId(100);
        request.setSongId(200);
        request.setPlayCount(5);
        request.setPlayTimeStamp(timestamp);

        assertThat(request.getId()).isEqualTo(1);
        assertThat(request.getUserId()).isEqualTo(100);
        assertThat(request.getSongId()).isEqualTo(200);
        assertThat(request.getPlayCount()).isEqualTo(5);
        assertThat(request.getPlayTimeStamp()).isEqualTo(timestamp);
    }

    @Test
    public void testPlayHistoryRequest_WithDefaultValues() {
        PlayHistoryRequest request = new PlayHistoryRequest();

        assertThat(request.getId()).isEqualTo(0);
        assertThat(request.getUserId()).isEqualTo(0);
        assertThat(request.getSongId()).isEqualTo(0);
        assertThat(request.getPlayCount()).isEqualTo(0);
        assertThat(request.getPlayTimeStamp()).isNull();
    }

    @Test
    public void testPlayHistoryRequest_UpdateFields() {
        Timestamp initialTimestamp = new Timestamp(System.currentTimeMillis());
        Timestamp updatedTimestamp = new Timestamp(System.currentTimeMillis() + 10000);

        PlayHistoryRequest request = new PlayHistoryRequest();
        request.setId(1);
        request.setUserId(100);
        request.setSongId(200);
        request.setPlayCount(5);
        request.setPlayTimeStamp(initialTimestamp);

        assertThat(request.getPlayTimeStamp()).isEqualTo(initialTimestamp);

        request.setPlayTimeStamp(updatedTimestamp);

        assertThat(request.getPlayTimeStamp()).isEqualTo(updatedTimestamp);
    }
}

