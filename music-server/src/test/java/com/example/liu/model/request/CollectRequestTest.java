package com.example.liu.model.request;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class CollectRequestTest {

    @Test
    public void testCollectRequest_WithValidInput() {
        Date currentDate = new Date();

        CollectRequest collectRequest = new CollectRequest();
        collectRequest.setId(1);
        collectRequest.setUserId(1001);
        collectRequest.setType((byte) 1);
        collectRequest.setSongId(501);
        collectRequest.setSongListId(10001);
        collectRequest.setCreateTime(currentDate);

        assertThat(collectRequest.getId()).isEqualTo(1);
        assertThat(collectRequest.getUserId()).isEqualTo(1001);
        assertThat(collectRequest.getType()).isEqualTo((byte) 1);
        assertThat(collectRequest.getSongId()).isEqualTo(501);
        assertThat(collectRequest.getSongListId()).isEqualTo(10001);
        assertThat(collectRequest.getCreateTime()).isEqualTo(currentDate);
    }

    @Test
    public void testCollectRequest_WithNullValues() {
        CollectRequest collectRequest = new CollectRequest();

        assertThat(collectRequest.getId()).isNull();
        assertThat(collectRequest.getUserId()).isNull();
        assertThat(collectRequest.getType()).isNull();
        assertThat(collectRequest.getSongId()).isNull();
        assertThat(collectRequest.getSongListId()).isNull();
        assertThat(collectRequest.getCreateTime()).isNull();
    }

    @Test
    public void testCollectRequest_UpdateCreateTime() {
        Date initialDate = new Date();
        Date updatedDate = new Date(System.currentTimeMillis() + 10000);

        CollectRequest collectRequest = new CollectRequest();
        collectRequest.setCreateTime(initialDate);

        assertThat(collectRequest.getCreateTime()).isEqualTo(initialDate);

        collectRequest.setCreateTime(updatedDate);

        assertThat(collectRequest.getCreateTime()).isEqualTo(updatedDate);
    }
}

