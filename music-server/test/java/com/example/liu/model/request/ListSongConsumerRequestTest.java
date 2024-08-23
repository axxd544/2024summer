package com.example.liu.model.request;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ListSongConsumerRequestTest {

    @Test
    public void testListSongConsumerRequest_WithValidInput() {
        ListSongConsumerRequest request = new ListSongConsumerRequest();
        request.setId(1);
        request.setSongId(2);
        request.setSongListConsumerId(3);

        assertThat(request.getId()).isEqualTo(1);
        assertThat(request.getSongId()).isEqualTo(2);
        assertThat(request.getSongListConsumerId()).isEqualTo(3);
    }

    @Test
    public void testListSongConsumerRequest_WithNullValues() {
        ListSongConsumerRequest request = new ListSongConsumerRequest();

        assertThat(request.getId()).isNull();
        assertThat(request.getSongId()).isNull();
        assertThat(request.getSongListConsumerId()).isNull();
    }

    @Test
    public void testListSongConsumerRequest_UpdateField() {
        ListSongConsumerRequest request = new ListSongConsumerRequest();
        request.setSongId(2);
        request.setSongListConsumerId(3);

        assertThat(request.getSongId()).isEqualTo(2);
        assertThat(request.getSongListConsumerId()).isEqualTo(3);

        request.setSongId(4);
        request.setSongListConsumerId(5);

        assertThat(request.getSongId()).isEqualTo(4);
        assertThat(request.getSongListConsumerId()).isEqualTo(5);
    }
}
