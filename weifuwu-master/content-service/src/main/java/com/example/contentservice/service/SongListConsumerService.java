package com.example.contentservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.contentservice.common.R;
import com.example.contentservice.model.domain.SongListConsumer;
import com.example.contentservice.model.request.SongListConsumerRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SongListConsumerService extends IService<SongListConsumer> {
    R addSongListConsumer(SongListConsumerRequest addSongListConsumerRequest);

    R updateSongListConsumerMsg(SongListConsumerRequest updateSongListConsumerRequest);

    R updateSongListConsumerImg(MultipartFile avatorFile, int id);

    R deleteSongListConsumer(Integer id);

    R deleteSongListConsumerByManager(Integer id, Integer complainterId);

    R unDeleteSongListConsumerByManager(Integer id, Integer complainterId, Integer applealerId);

    R allSongListConsumer();

    List<SongListConsumer> findAllSong();

    R likeTitle(String title);

    R likeStyle(String style);

    R eqUserId(Integer user_id);

    R byId(Integer id);
}
