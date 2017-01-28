package com.example;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@SpringBootApplication
public class DemoComplaintsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoComplaintsApplication.class, args);
	}

	@RestController
	public static class ComplaintAPI {

		private final ComplaintQueryObjectRepository repository;

		private final CommandGateway commandGateway;

		public ComplaintAPI(ComplaintQueryObjectRepository repository, CommandGateway commandGateway) {
			this.repository = repository;
			this.commandGateway = commandGateway;
		}

		@PostMapping
		public CompletableFuture<String> fileComplaint(@RequestBody Map<String, String> request) {
			String id = UUID.randomUUID().toString();
			return commandGateway.send(new FileComplaintCommand(id, request.get("company"), request.get("description")));
		}

		@GetMapping
		public List<ComplaintQueryObject> findAll() {
			return repository.findAll();
		}

		@GetMapping("/{id}")
		public ComplaintQueryObject find(@PathVariable String id) {
			return repository.findOne(id);
		}

		@Aggregate
        public static class Complaint {
		    @AggregateIdentifier
            private String complaintId;

            public Complaint() {
            }

            @CommandHandler
            public Complaint(FileComplaintCommand cmd) {
                Assert.hasLength(cmd.getCompany());

                apply(new ComplaintFileEvent(cmd.getId(), cmd.getCompany(), cmd.getDescription()));
            }

            @EventSourcingHandler
            public void on(ComplaintFileEvent event) {
                this.complaintId = event.getId();
            }
        }

        @Component
        public static class ComplaintQueryObjectUpdater {
            private final ComplaintQueryObjectRepository repository;

            public ComplaintQueryObjectUpdater(ComplaintQueryObjectRepository repository) {
                this.repository = repository;
            }

		    @EventHandler
            public void on(ComplaintFileEvent event) {
                repository.save(new ComplaintQueryObject(event.getId(), event.getCompany(), event.getDescription()));
            }
        }

		public static class FileComplaintCommand {

		    private final String id;
		    private final String company;
		    private final String description;

			public FileComplaintCommand(String id, String company, String description) {
                this.id = id;
                this.company = company;
                this.description = description;
			}

            public String getId() {
                return id;
            }

            public String getCompany() {
                return company;
            }

            public String getDescription() {
                return description;
            }
        }

        @Bean
        public Exchange exchange() {
		    return ExchangeBuilder.fanoutExchange("ComplaintEvents").build();
        }

        @Bean
        public Queue queue() {
		    return QueueBuilder.durable("CompliantEvents").build();
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
	}
}