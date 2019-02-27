package com.customer.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.customer.config.CustomerCreate;
import com.customer.config.CustomerUpdate;
import com.customer.model.CustomerEntity;
import com.customer.service.CustomerService;


@RestController
@RequestMapping(path =  "/api/customer", produces = {APPLICATION_JSON_VALUE})
public class CustomerController {

    private static final String SUBSTRING = "subString";

    private static final String ID = "id";

    @Autowired
    CustomerService customerService;

    @ResponseStatus(CREATED)
    @PostMapping
    public CustomerEntity createCustomer(@Validated(CustomerCreate.class) @RequestBody CustomerEntity customer) {
        return customerService.save(customer);
    }

    @ResponseStatus(OK)
    @PutMapping
    public CustomerEntity updateCustomer(@Validated(CustomerUpdate.class) @RequestBody CustomerEntity customer) {

        return customerService.update(customer);
    }
    
    @ResponseStatus(OK)
    @PutMapping(value = {"/delete/{id}"})
    public void deleteCustomer(@PathVariable(ID) Long id) {

        customerService.delete(id);
    }

    @GetMapping(value = {"/search/{subString}"})
    public List<CustomerEntity> searchCustomerByName(
            @PathVariable(value = SUBSTRING, required = false) Optional<String> subString) throws Exception {
        String searchString = subString.orElse("");
        return customerService.searchCustomersByName(searchString);
    }

    @GetMapping(value = "/{id}")
    public CustomerEntity getCustomerById(@PathVariable(ID) Long id) {
    	CustomerEntity customerEntity = customerService.getCustomerById(id);
        return customerEntity;
    }
}
