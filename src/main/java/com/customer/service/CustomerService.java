package com.customer.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.customer.config.Operation;
import com.customer.model.AddressRepository;
import com.customer.model.CustomerEntity;
import com.customer.model.CustomerRepository;

@Service
@Transactional
public class CustomerService {
	
	public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository) {
		this.customerRepository = customerRepository;
		this.addressRepository = addressRepository;
	}

    CustomerRepository customerRepository;
    
    AddressRepository addressRepository;

    public CustomerEntity save(CustomerEntity customer) {
    	customer.setAuditFields(Operation.CREATE,"system",null);
    	CustomerEntity customerEntity = customerRepository.save(customer);
    	customerEntity.getAddressEntities().stream().forEach(entity -> entity.setCustomer(customerEntity));
    	addressRepository.saveAll(customerEntity.getAddressEntities());
        return customerEntity;
    }
    
    public CustomerEntity update(CustomerEntity customer) {
    	customer.setAuditFields(Operation.UPDATE,"system",customer);
    	CustomerEntity customerEntity = customerRepository.save(customer);
    	customerEntity.getAddressEntities().stream().forEach(entity -> entity.setCustomer(customerEntity));
    	addressRepository.saveAll(customerEntity.getAddressEntities());
        return customerEntity;
    }
    
    public void delete(Long id) {
    	customerRepository.delete(customerRepository.findById(id).get());
    }

    public List<CustomerEntity> searchCustomersByName(String subString) {
        return customerRepository.findAllByName(subString.toUpperCase()).collect(toList());
    }

    public CustomerEntity getCustomerById(Long id) {
        return customerRepository.findById(id).get();
    }
}
