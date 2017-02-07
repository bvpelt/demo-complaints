package nl.bsoft.configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by bvpelt on 2/7/17.
 */
@Configuration
public class RabbitMQ {

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
}
