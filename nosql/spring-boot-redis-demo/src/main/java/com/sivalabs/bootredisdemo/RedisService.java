package com.sivalabs.bootredisdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RedisService {
    private static final String KEY = "daychange";

    private final RedisTemplate<String, ?> redisTemplate;

    public Map<String, String> addItems(int count) {
        Map<String, String> data = new HashMap<>();
        for (int i = 0; i < count; i++) {
            data.put("lenderId-"+i+":lmsId-"+i, "QUEUED");
        }
        redisTemplate.opsForHash().putAll(KEY, data);
        redisTemplate.expireAt(KEY, new Date(System.currentTimeMillis() + 10 * 60 * 1000));
        return data;
    }

    public void updateItems(Map<String,String> params) {
        redisTemplate.opsForHash().putAll(KEY, params);
    }

    public Map<String, String> getItems() {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(KEY);
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<Object, Object> entry : entries.entrySet()) {
            result.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        }
        return result;
    }

    public void deleteItems(@RequestBody Map<String, String> params) {
        Object[] keys = params.keySet().stream().map(String::valueOf).toArray();
        redisTemplate.opsForHash().delete(KEY, keys);
    }

    public void deleteAllItems() {
        redisTemplate.delete(KEY);
    }
}
