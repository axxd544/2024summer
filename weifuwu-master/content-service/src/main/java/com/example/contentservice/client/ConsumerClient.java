package com.example.contentservice.client;

import com.example.contentservice.model.domain.Consumer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="consumer-service")
public interface ConsumerClient
{
    @GetMapping("/user/by-singername")
    Consumer getConsumerByUsername(@RequestParam("username") String username);

    @GetMapping("/user/detail")
    Consumer userOfId(@RequestParam int id);

}
