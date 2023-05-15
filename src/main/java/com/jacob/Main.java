package com.jacob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@RestController
@RequestMapping("/customer")
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);
    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        logger.info("************************");
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");
        logger.info("************************");

        logger.info("Available Processors : "+Runtime.getRuntime().availableProcessors());
        logger.info("Version : "+Runtime.version().toString());
        logger.info("Max Memory : "+Runtime.getRuntime().maxMemory());
        logger.info("Total Memory : "+Runtime.getRuntime().totalMemory());
        logger.info("************************");

        int[] arr = {1,2,3, 4, 20, 6};
        var res = Arrays.stream(arr)
                .sorted()
                .boxed()
                .toList()
                .get(arr.length - 2);
        System.out.println(res);


        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        SpringApplication.run(Main.class, args);
    }

    @GetMapping
    @RequestMapping("/list")
    public List<Customer> listCustomers(){
        return customerRepository.findAll();
    }

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer){
        return customerRepository.save( new Customer(customer.getName(), customer.getEmail(), customer.getAge()));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCustomer(@PathVariable("id") UUID id){
        customerRepository.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public Customer updateCustomer(@PathVariable("id") UUID id, @RequestBody Customer customer ){
        var a = customerRepository.findById(id).orElseThrow();
        a.setAge(customer.getAge());
        a.setEmail(customer.getEmail());
        a.setName(customer.getName());
        return customerRepository.save(a);
    }

    @GetMapping("/get/{id}")
    public Customer getCustomer(@PathVariable("id") UUID id){
        return customerRepository.findById(id).orElseThrow();
    }


}
