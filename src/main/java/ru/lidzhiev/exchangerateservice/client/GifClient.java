package ru.lidzhiev.exchangerateservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "https://api.giphy.com/v1/gifs/", name = "User1")
public interface GifClient {
    @GetMapping("search")
    public ResponseEntity<String> getGif(@RequestParam String api_key, @RequestParam String q, @RequestParam int limit);
}
