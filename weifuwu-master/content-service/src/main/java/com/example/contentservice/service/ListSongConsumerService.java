package com.example.contentservice.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.contentservice.common.R;
import com.example.contentservice.model.domain.ListSongConsumer;
import com.example.contentservice.model.request.ListSongConsumerRequest;

import java.util.List;

public interface ListSongConsumerService extends IService<ListSongConsumer> {
    R addListSongConsumer(ListSongConsumerRequest addListSongConsumerRequest);

    R updateListSongConsumerMsg(ListSongConsumerRequest updateListSongConsumerRequest);

    R deleteListSongConsumer(Integer songId, Integer songListConsumerId);

    List<ListSongConsumer> allListSongConsumer();

    R listSongConsumerOfSongId(Integer songListConsumerId);
}
