package com.okdeer.mall.redis.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2018/5/1 0001.
 */
public class Receiver {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void receiveMessage(String message){
        logger.info("Receiver :"+message);
    }
}
