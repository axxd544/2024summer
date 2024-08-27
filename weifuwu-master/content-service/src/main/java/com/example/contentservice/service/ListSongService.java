package com.example.contentservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.contentservice.common.R;
import com.example.contentservice.model.domain.ListSong;
import com.example.contentservice.model.request.ListSongRequest;

import java.util.List;

public interface ListSongService extends IService<ListSong> {

    R addListSong(ListSongRequest addListSongRequest);

    R updateListSongMsg(ListSongRequest updateListSongRequest);

    R deleteListSong(Integer songId, Integer songListId);

    //看看这啥
    List<ListSong> allListSong();

    R listSongOfSongId(Integer songListId);
}
