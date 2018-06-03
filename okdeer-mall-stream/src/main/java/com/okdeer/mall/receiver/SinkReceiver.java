package com.okdeer.mall.receiver;

import com.okdeer.mall.StreamApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/6/2 0002.
 */
@EnableBinding(Sink.class)
@Component
public class SinkReceiver {

    private static Logger LOGGER = LoggerFactory.getLogger(StreamApplication.class);

    @StreamListener(Sink.INPUT)
    public void receiver(Object payload){
        LOGGER.info("Receive: "+payload);
    }
}
