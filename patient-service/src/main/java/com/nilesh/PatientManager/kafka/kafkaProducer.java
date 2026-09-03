package com.nilesh.PatientManager.kafka;

import com.nilesh.PatientManager.model.Patient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import patient.event.PatientEvent;

@Service
@Slf4j
public class kafkaProducer {
    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    //how we define our msg type and use it to send msg to kafka topic

    public kafkaProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(Patient patient) {
        PatientEvent event= PatientEvent.newBuilder()
                .setPatientId(patient.getId().toString())
                .setName(patient.getName())
                .setEmail(patient.getEmail())
                .setEventType("PATIENT_CREATED")
                .setTimestamp(System.currentTimeMillis())
                .build();

        try {
            kafkaTemplate.send("patient", event.toByteArray());
        } catch (Exception e) {
            log.error("Error sending patient event to Kafka: {}",event);
            throw new RuntimeException(e);
        }
    }

}
