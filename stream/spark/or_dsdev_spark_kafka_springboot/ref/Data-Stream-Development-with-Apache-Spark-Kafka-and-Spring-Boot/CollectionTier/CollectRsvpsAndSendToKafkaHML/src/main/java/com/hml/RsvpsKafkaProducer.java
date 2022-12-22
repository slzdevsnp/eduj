package com.hml;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Source.class)
public class RsvpsKafkaProducer {

    private static final Logger logger
            = Logger.getLogger(RsvpsKafkaProducer.class.getName());

    private static final int SENDING_MESSAGE_TIMEOUT_MS = 1000;

    private final Source source;

    public RsvpsKafkaProducer(Source source) {
        this.source = source;
    }

    public void sendRsvpMessage(RsvpDocument rsvp) {

        logger.info(() -> "Sending RSVP with id: " + rsvp.getId());

        try {
            source.output()
                    .send(MessageBuilder.withPayload(rsvp.getData())
                            .build(),
                            SENDING_MESSAGE_TIMEOUT_MS);

            rsvp.setStatus(RsvpStatus.PROCESSED);
        } catch (Exception e) {
            if(rsvp.getStatus().equals(RsvpStatus.PENDING)) {
               rsvp.setStatus(RsvpStatus.UNSENT);
            }
            
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
