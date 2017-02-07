package nl.bsoft.api;

import nl.bsoft.command.FileComplaintCommand;
import nl.bsoft.core.Complaint;
import nl.bsoft.persistence.ComplaintQueryObjectRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Created by bvpelt on 2/7/17.
 */

@RequestMapping("/complaints")
@RestController
public class ComplaintAPI {

    private final Logger log = LoggerFactory.getLogger(ComplaintAPI.class);

    private final CommandGateway commandGateway;
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

    /*
    @Bean
    public Exchange exchange() {
        return ExchangeBuilder.fanoutExchange("Complaints").build();
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable("Complaints").build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with("*").noargs();
    }

    @Autowired
    public void configure(AmqpAdmin admin) {
        admin.declareExchange(exchange());
        admin.declareQueue(queue());
        admin.declareBinding(binding());
    }
    */
}