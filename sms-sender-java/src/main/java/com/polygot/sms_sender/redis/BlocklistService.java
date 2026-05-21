package com.polygot.sms_sender.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class BlocklistService {

    private final StringRedisTemplate redisTemplate;

    public BlocklistService(
            StringRedisTemplate redisTemplate
    ) {
        this.redisTemplate = redisTemplate;
    }

    public boolean isBlocked(String userId) {

        String key = "blocked:" + userId;

        String value = redisTemplate.opsForValue().get(key);

        return value != null && value.equals("true");
    }
}