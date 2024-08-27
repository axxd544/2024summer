package com.example.socialservice.client;

import com.example.socialservice.model.domain.Consumer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="consumer-service")
public interface ConsumerClient
{
    @GetMapping("/consumers/by-username")
    Consumer getConsumerByUsername(@RequestParam("username") String username);

    @GetMapping("/user/detail")
    Consumer userOfId(@RequestParam("id") int id);

}
