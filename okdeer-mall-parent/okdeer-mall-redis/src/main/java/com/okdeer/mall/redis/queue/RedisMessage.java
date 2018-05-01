package com.okdeer.mall.redis.queue;

import java.io.Serializable;

/**
 * Created by Lenovo on 2017/11/8.
 */
public class RedisMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private Object content;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getContent() {
        return this.content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
