package com.project.weezdompet.services;

import com.project.weezdompet.data.entities.CustomerEntity;
import com.project.weezdompet.data.repositories.CustomerRepository;
import com.project.weezdompet.web.errors.NotFoundException;
import com.project.weezdompet.web.models.Customer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    /*This method returns a list of customers.
    If you give it an email (filterEmail), it returns only that customer.
    If you don't give an email, it returns all customers.
    It takes data from the database (CustomerEntity) and converts it into a format used by the web (Customer).
    */
    public List<Customer> getAllCustomers(String filterEmail) {
        List<Customer> customers = new ArrayList<>();
        if(StringUtils.hasLength(filterEmail)){
            CustomerEntity entity = this.customerRepository.findByEmail(filterEmail);
            customers.add(this.translateDbToWeb(entity));
        } else {
            Iterable<CustomerEntity> entities = this.customerRepository.findAll();
            entities.forEach(entity -> customers.add(this.translateDbToWeb(entity)));
        }
        return customers;

    }

    public Customer getCustomer(long id) {
        Optional<CustomerEntity> optional = this.customerRepository.findById(id);
        if(optional.isEmpty()) {
            throw new NotFoundException("Customer with id " + id + " not found");
        }
        return this.translateDbToWeb(optional.get());
    }

    public Customer createOrUpdateCustomer(Customer customer){
        CustomerEntity entity = this.translateWebToDb(customer);
        entity = this.customerRepository.save(entity);
        return this.translateDbToWeb(entity);
    }

    public void deleteCustomer(long id) {
        this.customerRepository.deleteById(id);
    }


    private CustomerEntity translateWebToDb(Customer customer) {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(customer.getId());
        entity.setFirstName(customer.getFirstName());
        entity.setLastName(customer.getLastName());
        entity.setEmail(customer.getEmailAddress());
        entity.setPhone(customer.getPhoneNumber());
        entity.setAddress(customer.getAddress());
        return entity;
    }

    private Customer translateDbToWeb(CustomerEntity entity) {
        return new Customer(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getPhone(), entity.getAddress());
    }
}
