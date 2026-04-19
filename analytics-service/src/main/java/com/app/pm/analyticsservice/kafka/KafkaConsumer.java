package com.app.pm.analyticsservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.protobuf.InvalidProtocolBufferException;

import patient.events.PatientEvent;

@Service
public class KafkaConsumer {

    private static final Logger log =
        LoggerFactory.getLogger(KafkaConsumer.class);

    private static final String TOPIC = "patient";

    @KafkaListener(
        topics = TOPIC,
        groupId = "analytics-service",
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumeEvent(byte[] event) {

        try {

            PatientEvent patientEvent =
                PatientEvent.parseFrom(event);

            log.info(
                "Received Patient Event: {}",
                patientEvent
            );

            // analytics logic here

        } catch (InvalidProtocolBufferException e) {

            log.error(
                "Error deserializing PatientEvent",
                e
            );

        }

    }

}