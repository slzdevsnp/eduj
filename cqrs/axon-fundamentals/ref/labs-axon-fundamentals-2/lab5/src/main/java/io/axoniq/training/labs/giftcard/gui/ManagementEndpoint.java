package io.axoniq.training.labs.giftcard.gui;

import org.axonframework.config.EventProcessingConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ops")
public class ManagementEndpoint {

    private final EventProcessingConfiguration eventProcessingConfiguration;

    public ManagementEndpoint(EventProcessingConfiguration eventProcessingConfiguration) {
        this.eventProcessingConfiguration = eventProcessingConfiguration;
    }

    @PostMapping("/eventProcessor/{processorName}/reset")
    public ResponseEntity resetEventProcessor(@PathVariable String processorName) {
        // TODO perform the reset operation here...
        return ResponseEntity.ok().body("Nothing happened, yet...");
    }
}