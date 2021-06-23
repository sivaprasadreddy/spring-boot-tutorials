package com.sivalabs.bootredisdemo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RedisRestController {
    private final Initializer initializer;
    private final RedisService redisService;

    @GetMapping("/api/test")
    public void runTest() {
        initializer.run();
    }

    @PostMapping("/api/add-items")
    public void addItems(@RequestParam(name = "count", defaultValue = "10")int count) {
        redisService.addItems(count);
    }

    @PutMapping("/api/update-items")
    public void updateItems(@RequestBody Map<String,String> params) {
        redisService.updateItems(params);
    }

    @GetMapping("/api/get-items")
    public Map<String, String> getItems() {
        return redisService.getItems();
    }

    @DeleteMapping("/api/delete-items")
    public void deleteItems(@RequestBody Map<String, String> params) {
        redisService.deleteItems(params);
    }
}
