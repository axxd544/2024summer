package com.example.contentservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.contentservice.common.R;
import com.example.contentservice.model.domain.SongList;
import com.example.contentservice.model.request.SongListRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SongListService extends IService<SongList> {

    R addSongList(SongListRequest addSongListRequest);

    R updateSongListMsg(SongListRequest updateSongListRequest);

    R updateSongListImg(MultipartFile avatorFile, int id);

    R deleteSongList(Integer id);

    R allSongList();

    List<SongList> findAllSong();

    R likeTitle(String title);

    R likeStyle(String style);
}
