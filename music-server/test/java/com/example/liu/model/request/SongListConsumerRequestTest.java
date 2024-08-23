package com.example.liu.model.request;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SongListConsumerRequestTest {

    @Test
    public void testSongListConsumerRequest_WithValidInput() {
        SongListConsumerRequest request = new SongListConsumerRequest();
        request.setId(1);
        request.setUserId(100);
        request.setTitle("My Favorite Songs");
        request.setPic("pic_url");
        request.setStyle("Pop");
        request.setIntroduction("A collection of my favorite pop songs.");

        assertThat(request.getId()).isEqualTo(1);
        assertThat(request.getUserId()).isEqualTo(100);
        assertThat(request.getTitle()).isEqualTo("My Favorite Songs");
        assertThat(request.getPic()).isEqualTo("pic_url");
        assertThat(request.getStyle()).isEqualTo("Pop");
        assertThat(request.getIntroduction()).isEqualTo("A collection of my favorite pop songs.");
    }

    @Test
    public void testSongListConsumerRequest_WithNullValues() {
        SongListConsumerRequest request = new SongListConsumerRequest();

        assertThat(request.getId()).isNull();
        assertThat(request.getUserId()).isNull();
        assertThat(request.getTitle()).isNull();
        assertThat(request.getPic()).isNull();
        assertThat(request.getStyle()).isNull();
        assertThat(request.getIntroduction()).isNull();
    }

    @Test
    public void testSongListConsumerRequest_UpdateFields() {
        SongListConsumerRequest request = new SongListConsumerRequest();
        request.setTitle("Old Title");
        assertThat(request.getTitle()).isEqualTo("Old Title");

        request.setTitle("New Title");
        assertThat(request.getTitle()).isEqualTo("New Title");
    }
}
