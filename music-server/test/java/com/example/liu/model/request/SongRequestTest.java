package com.example.liu.model.request;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class SongRequestTest {

    @Test
    public void testSongRequest_WithValidInput() {
        Date currentDate = new Date();

        SongRequest request = new SongRequest();
        request.setId(1);
        request.setSingerId(100);
        request.setName("Song Title");
        request.setIntroduction("Song Introduction");
        request.setCreateTime(currentDate);
        request.setUpdateTime(currentDate);
        request.setPic("pic_url");
        request.setLyric("Song Lyrics");
        request.setUrl("song_url");

        assertThat(request.getId()).isEqualTo(1);
        assertThat(request.getSingerId()).isEqualTo(100);
        assertThat(request.getName()).isEqualTo("Song Title");
        assertThat(request.getIntroduction()).isEqualTo("Song Introduction");
        assertThat(request.getCreateTime()).isEqualTo(currentDate);
        assertThat(request.getUpdateTime()).isEqualTo(currentDate);
        assertThat(request.getPic()).isEqualTo("pic_url");
        assertThat(request.getLyric()).isEqualTo("Song Lyrics");
        assertThat(request.getUrl()).isEqualTo("song_url");
    }

    @Test
    public void testSongRequest_WithNullValues() {
        SongRequest request = new SongRequest();

        assertThat(request.getId()).isNull();
        assertThat(request.getSingerId()).isNull();
        assertThat(request.getName()).isNull();
        assertThat(request.getIntroduction()).isNull();
        assertThat(request.getCreateTime()).isNull();
        assertThat(request.getUpdateTime()).isNull();
        assertThat(request.getPic()).isNull();
        assertThat(request.getLyric()).isNull();
        assertThat(request.getUrl()).isNull();
    }

    @Test
    public void testSongRequest_UpdateFields() {
        Date initialDate = new Date();
        Date updatedDate = new Date(initialDate.getTime() + 10000);

        SongRequest request = new SongRequest();
        request.setCreateTime(initialDate);
        assertThat(request.getCreateTime()).isEqualTo(initialDate);

        request.setCreateTime(updatedDate);
        assertThat(request.getCreateTime()).isEqualTo(updatedDate);
    }
}
