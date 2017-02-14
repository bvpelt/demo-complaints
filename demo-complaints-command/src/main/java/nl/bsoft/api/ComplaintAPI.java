package nl.bsoft.api;

import nl.bsoft.command.FileComplaintCommand;
import nl.bsoft.core.Complaint;
import nl.bsoft.persistence.ComplaintQueryObjectRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Created by bvpelt on 2/7/17.
 */

@RequestMapping("/complaints")
@RefreshScope
@RestController
public class ComplaintAPI {

    private final Logger log = LoggerFactory.getLogger(ComplaintAPI.class);

    @Autowired
    private CommandGateway commandGateway;

    private final ComplaintQueryObjectRepository complaintsQueryObjectRepository;


    public ComplaintAPI(CommandGateway commandGateway, ComplaintQueryObjectRepository complaintsQueryObjectRepository) {
        this.complaintsQueryObjectRepository = complaintsQueryObjectRepository;
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public CompletableFuture<String> fileComplaint(@RequestBody Map<String, String> request) {
        String id = UUID.randomUUID().toString();
        log.info("file complaint id: {}, company: {}, description: {}", id, request.get("company"), request.get("description"));
        return commandGateway.send(new FileComplaintCommand(id, request.get("company"), request.get("description")));
    }

    @GetMapping
    public List<Complaint> findAll() {
        return complaintsQueryObjectRepository.findAll();
    }

    @GetMapping("/{id}")
    public Complaint find(@PathVariable String id) {
        return complaintsQueryObjectRepository.findOne(id);
    }

}