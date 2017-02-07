package nl.bsoft.aggregates;

import nl.bsoft.command.FileComplaintCommand;
import nl.bsoft.events.ComplaintFileEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * Created by bvpelt on 2/7/17.
 */

@Aggregate
public class ComplaintAggregate {
    private final Logger log = LoggerFactory.getLogger(ComplaintAggregate.class);

    @AggregateIdentifier
    private String complaintId;

    public ComplaintAggregate() {
    }

    @CommandHandler
    public ComplaintAggregate(FileComplaintCommand command) {
        Assert.hasLength(command.getCompany());
        log.info("apply filecomplaint id: {}, company: {}, description: {}", command.getId(), command.getCompany(), command.getDescription());
        apply(new ComplaintFileEvent(command.getId(), command.getCompany(), command.getDescription()));
    }

    @EventSourcingHandler
    public void on(ComplaintFileEvent event) {
        this.complaintId = event.getId();
    }
}