package nl.bsoft;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoComplaintsApplication {

    private final Logger log = LoggerFactory.getLogger(DemoComplaintsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoComplaintsApplication.class, args);
    }
    
}
