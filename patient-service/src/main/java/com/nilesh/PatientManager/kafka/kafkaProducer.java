package com.nilesh.PatientManager.kafka;

import org.springframework.kafka.core.KafkaTemplate;

public class kafkaProducer {
    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    //how we define our msg type and use it to send msg to kafka topic

    public kafkaProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

}
