package com.blinkgift.client;

import com.blinkgift.dto.GiftResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gift-discovery", url = "http://localhost:7781")
public interface GiftDiscoveryClient {

    @GetMapping("/api/v1/gifts/search")
    GiftResponseDto getGiftByName(@RequestParam("name") String name);
}