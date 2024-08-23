package com.example.liu.model.request;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ListSongRequestTest {

    @Test
    public void testListSongRequest_WithValidInput() {
        ListSongRequest request = new ListSongRequest();
        request.setId(1);
        request.setSongId(2);
        request.setSongListId(3);

        assertThat(request.getId()).isEqualTo(1);
        assertThat(request.getSongId()).isEqualTo(2);
        assertThat(request.getSongListId()).isEqualTo(3);
    }

    @Test
    public void testListSongRequest_WithNullValues() {
        ListSongRequest request = new ListSongRequest();

        assertThat(request.getId()).isNull();
        assertThat(request.getSongId()).isNull();
        assertThat(request.getSongListId()).isNull();
    }

    @Test
    public void testListSongRequest_UpdateField() {
        ListSongRequest request = new ListSongRequest();
        request.setSongId(2);
        request.setSongListId(3);

        assertThat(request.getSongId()).isEqualTo(2);
        assertThat(request.getSongListId()).isEqualTo(3);

        request.setSongId(4);
        request.setSongListId(5);

        assertThat(request.getSongId()).isEqualTo(4);
        assertThat(request.getSongListId()).isEqualTo(5);
    }
}
