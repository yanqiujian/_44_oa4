package com.qianfeng.oa.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class OACache implements IOACache {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void saveHash(String key, Map<String, Object> value) {
        redisTemplate.boundHashOps(key)
                .putAll(value);
    }

    public Map<Object, Object> queryHashByKey(String key) {
        Map<Object, Object> map = redisTemplate.boundHashOps(key)
                .entries();
        return map;
    }

    public void saveList(String key , String processId) {
        redisTemplate.boundListOps(key)
                .leftPush(processId);
        System.out.println("nishiyigeguapi");

    }

    public List<String> getAllProcessIdByKey(String key,int startIndex,int endIndex) {
        List<String> list = redisTemplate.boundListOps(key)
                .range(startIndex, endIndex);

        return list;
    }
}
