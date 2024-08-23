package com.example.liu.model.request;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RankListRequestTest {

    @Test
    public void testRankListRequest_WithValidInput() {
        RankListRequest request = new RankListRequest();
        request.setId(1L);
        request.setSongListId(2L);
        request.setConsumerId(3L);
        request.setScore(100);

        assertThat(request.getId()).isEqualTo(1L);
        assertThat(request.getSongListId()).isEqualTo(2L);
        assertThat(request.getConsumerId()).isEqualTo(3L);
        assertThat(request.getScore()).isEqualTo(100);
    }

    @Test
    public void testRankListRequest_WithDefaultValues() {
        RankListRequest request = new RankListRequest();

        assertThat(request.getId()).isNull();
        assertThat(request.getSongListId()).isNull();
        assertThat(request.getConsumerId()).isNull();
        assertThat(request.getScore()).isNull();
    }

    @Test
    public void testRankListRequest_UpdateFields() {
        RankListRequest request = new RankListRequest();
        request.setId(1L);
        request.setSongListId(2L);
        request.setConsumerId(3L);
        request.setScore(50);

        assertThat(request.getScore()).isEqualTo(50);

        request.setScore(75);

        assertThat(request.getScore()).isEqualTo(75);
    }
}
