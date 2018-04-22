package com.okdeer.mall.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.StringUtils;

/**
 * 手动进行redis缓存
 * Created by Administrator on 2018/4/21 0021.
 */
public class RedisCacheTest {

    // 还有RedisTemplate等其他的工具 根据实际的情况选择
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/redis/{key}/{value}",method = RequestMethod.GET)
    @ResponseBody
    // 这里的注解只是作为示例 实际应该配置在service方法上面 需要自动注解的配置

    // 这里注解以后 缓存里面会有两种数据 一种是按照redisconfig里面设置的
    // 使用类名 + 方法名 + 参数作为缓存key的数据 其中value就返回对象序列化以后的结果
    //一种是有序集合。其中key为 @Cacheable中的value + ~keys.其中value为String类型的键值对的key.
    // 下面注解生成的有序集合为"userCache~keys"
    @Cacheable(value = "userCache")
    public String redisTest(@PathVariable String key, @PathVariable String value) {
        String redisValue = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(redisValue)) {
            stringRedisTemplate.opsForValue().set(key,value);
            return "操作成功！";
        }

        if (!redisValue.equals(value)) {
            stringRedisTemplate.opsForValue().set(key,value);
            return "操作成功！";
        }

        return String.format("redis中已存在[key=%s,value=%s]的数据！",key,value);
    }

}
