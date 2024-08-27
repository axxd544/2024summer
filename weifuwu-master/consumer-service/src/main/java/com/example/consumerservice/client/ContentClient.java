package com.example.consumerservice.client;


import com.example.consumerservice.model.domain.Song;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="content-service")
public interface ContentClient {
    @GetMapping("/song/detail")
    Song songOfId(@RequestParam("id") int songid);

}
