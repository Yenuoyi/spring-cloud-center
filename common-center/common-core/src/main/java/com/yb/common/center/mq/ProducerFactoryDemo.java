package com.yb.common.center.mq;

import com.yb.common.center.mq.impl.ProducerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yebing
 */
@Configuration
public class ProducerFactoryDemo {
    @Bean("producerGroup")
    public ProducerClient createProducer(){
        ProducerClient producerClient = new ProducerClient();
        producerClient.setProducerGroup("group");
        producerClient.setNamesrvAddr("name");
        return producerClient;
    }
}
