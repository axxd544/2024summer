package com.example.liu.model.request;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SongListRequestTest {

    @Test
    public void testSongListRequest_WithValidInput() {
        SongListRequest request = new SongListRequest();
        request.setId(1);
        request.setTitle("Top Hits");
        request.setPic("pic_url");
        request.setStyle("Rock");
        request.setIntroduction("A collection of top rock hits.");

        assertThat(request.getId()).isEqualTo(1);
        assertThat(request.getTitle()).isEqualTo("Top Hits");
        assertThat(request.getPic()).isEqualTo("pic_url");
        assertThat(request.getStyle()).isEqualTo("Rock");
        assertThat(request.getIntroduction()).isEqualTo("A collection of top rock hits.");
    }

    @Test
    public void testSongListRequest_WithNullValues() {
        SongListRequest request = new SongListRequest();

        assertThat(request.getId()).isNull();
        assertThat(request.getTitle()).isNull();
        assertThat(request.getPic()).isNull();
        assertThat(request.getStyle()).isNull();
        assertThat(request.getIntroduction()).isNull();
    }

    @Test
    public void testSongListRequest_UpdateFields() {
        SongListRequest request = new SongListRequest();
        request.setTitle("Old Title");
        assertThat(request.getTitle()).isEqualTo("Old Title");

        request.setTitle("New Title");
        assertThat(request.getTitle()).isEqualTo("New Title");
    }
}
