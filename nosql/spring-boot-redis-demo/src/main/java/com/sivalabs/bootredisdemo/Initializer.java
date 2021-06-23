package com.sivalabs.bootredisdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Initializer /*implements CommandLineRunner*/ {
    private final RedisService redisService;

    //@Override
    public void run(String... args){
        redisService.deleteAllItems();
        long start = System.currentTimeMillis();
        int count = 5000;
        Map<String, String> data = redisService.addItems(count);
        int batch = 0;
        Set<String> keys = data.keySet();
        Map<String, String> updates = new HashMap<>();
        for (String key : keys) {
            updates.put(key, "PROCESSED");
            batch++;
            if (batch == 20) {
                redisService.updateItems(updates);
                batch = 0;
                updates = new HashMap<>();
            }
        }
        if( batch > 0) {
            redisService.updateItems(updates);
        }
        Map<String, String> items = redisService.getItems();
        //System.out.println(items);
        long end = System.currentTimeMillis();
        System.out.println("Time taken: "+(end-start)+" millis");
    }
}
