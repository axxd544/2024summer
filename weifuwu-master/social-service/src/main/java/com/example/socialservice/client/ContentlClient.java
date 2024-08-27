package com.example.socialservice.client;

import com.example.socialservice.model.domain.Follow;
import com.example.socialservice.model.domain.Singer;
import com.example.socialservice.model.domain.Song;
import com.example.socialservice.model.domain.SongListConsumer;
import com.example.socialservice.model.request.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@FeignClient(name="social-service")
public interface ContentlClient {

    //按照id查找歌名
    @GetMapping("/song/detail")
    Song songOfId(@RequestParam("id") int id );
    //按照id查找歌手
    @GetMapping("/singer/id/detail")
    Singer singerOfId(@RequestParam("id") int id);
    //按照id查找用户歌单
    @GetMapping("/songListConsumer/byId/detail")
    SongListConsumer songListConsumerOfId(@RequestParam( "id") int id);

}
