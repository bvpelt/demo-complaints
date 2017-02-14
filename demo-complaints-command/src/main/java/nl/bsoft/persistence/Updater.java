package nl.bsoft.persistence;

import nl.bsoft.core.Complaint;
import nl.bsoft.events.ComplaintFileEvent;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by bvpelt on 2/7/17.
 */

@Component
public class Updater {

    private final Logger log = LoggerFactory.getLogger(Updater.class);

    private final ComplaintQueryObjectRepository complaintQueryObjectRepository;

    public Updater(ComplaintQueryObjectRepository complaintQueryObjectRepository) {
        this.complaintQueryObjectRepository = complaintQueryObjectRepository;
    }

    @EventHandler
    public void on(ComplaintFileEvent event) {
        log.info("Save complaint in repository id: {}, company: {}, description: {}", event.getId(), event.getCompany(), event.getDescription());
        complaintQueryObjectRepository.save(new Complaint(event.getId(), event.getCompany(), event.getDescription()));
    }
}