package com.project.weezdompet.web.rest;

import com.project.weezdompet.services.CustomerService;
import com.project.weezdompet.web.errors.BadRequestException;
import com.project.weezdompet.web.models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    private final CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> getAll(@RequestParam(name = "email", required = false) String email) {
        return this.customerService.getAllCustomers(email);
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable long id) {
        return this.customerService.getCustomer(id);
    }

    @PostMapping("/createCustomer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer) {
        return this.customerService.createOrUpdateCustomer(customer);
    }

    @PutMapping("updateCustomer/{id}")
    public Customer updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
        if (id != customer.getId()) {
            throw new BadRequestException("Customer id mismatch");
        }
        return this.customerService.createOrUpdateCustomer(customer);
    }

    @DeleteMapping("deleteCustomer/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteCustomer(@PathVariable("id") long id) {
        this.customerService.deleteCustomer(id);
    }

}
