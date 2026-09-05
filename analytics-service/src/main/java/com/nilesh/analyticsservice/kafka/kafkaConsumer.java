package com.nilesh.analyticsservice.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.event.PatientEvent;

@Service
@Slf4j
public class kafkaConsumer {
    @KafkaListener(topics = "patient", groupId = "analytics-service")
    public void consumeEvent(byte[] event) {
        PatientEvent patientEvent;
        try {
            // perform any analytics business logic here
            patientEvent = PatientEvent.parseFrom(event);
            log.info("Received event: [PatientId={},PatientName={},PatientEmail={}]"
                    , patientEvent.getPatientId()
                    ,patientEvent.getName()
                    ,patientEvent.getEmail());
        } catch (InvalidProtocolBufferException e) {
            log.error("Failed to parse PatientEvent from Kafka message", e);
            throw new RuntimeException(e);
        }
        log.info("Received event: " + patientEvent);
    }
}
