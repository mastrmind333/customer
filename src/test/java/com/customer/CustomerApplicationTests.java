package com.customer;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.customer.CustomerApplication;
import com.customer.config.Gender;
import com.customer.config.MaritalStatus;
import com.customer.config.Status;
import com.customer.controller.CustomerController;
import com.customer.model.AddressRepository;
import com.customer.model.CustomerEntity;
import com.customer.model.CustomerRepository;
import com.customer.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class CustomerApplicationTests {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CustomerController customerController;
    
	@Autowired
    AddressRepository addressRepository;
	
	@Before
	public void setUp() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setFirstName("Sunil");
		customerEntity.setMiddleName("Krishna");
		customerEntity.setLastName("Behera");
		customerEntity.setGender(Gender.MALE);
		customerEntity.setStatus(Status.ENABLED);
		customerEntity.setIsExistingCustomer(Boolean.TRUE);
		customerEntity.setMaritalStatus(MaritalStatus.MARRIED);
		customerEntity.setCreatedBy("system");
		customerEntity.setVersion(1);
		customerController.createCustomer(customerEntity);
	}
	
	@Test
	public void searchCustomerByName() {
		try {
			CustomerEntity customerEntity = new CustomerEntity();
			customerEntity.setFirstName("Sunil");
			customerEntity.setMiddleName("Krishna");
			customerEntity.setLastName("Behera");
			customerEntity.setGender(Gender.MALE);
			customerEntity.setStatus(Status.ENABLED);
			customerEntity.setIsExistingCustomer(Boolean.TRUE);
			customerEntity.setMaritalStatus(MaritalStatus.MARRIED);
			customerEntity.setCreatedBy("system");
			customerEntity.setVersion(1);
			customerController.createCustomer(customerEntity);
			assertEquals("Sunil",customerController.searchCustomerByName(Optional.of("sun")).get(0).getFirstName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void updateCustomerController() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(1l);
		customerEntity.setFirstName("Sunil");
		customerEntity.setMiddleName("Krishna");
		customerEntity.setLastName("Behera");
		customerEntity.setGender(Gender.MALE);
		customerEntity.setStatus(Status.ENABLED);
		customerEntity.setIsExistingCustomer(Boolean.TRUE);
		customerEntity.setMaritalStatus(MaritalStatus.MARRIED);
		customerEntity.setCreatedBy("system");
		customerEntity.setVersion(1);
		customerService.save(customerEntity);
	}
	
	@Test
	public void deleteCustomerController() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setFirstName("Sunil");
		customerEntity.setMiddleName("Krishna");
		customerEntity.setLastName("Behera");
		customerEntity.setGender(Gender.MALE);
		customerEntity.setStatus(Status.ENABLED);
		customerEntity.setIsExistingCustomer(Boolean.TRUE);
		customerEntity.setMaritalStatus(MaritalStatus.MARRIED);
		customerEntity.setCreatedBy("system");
		customerEntity.setVersion(1);
		customerController.deleteCustomer(customerController.createCustomer(customerEntity).getId());
	}
	
	
	
	@Test
	public void saveCustomer() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setFirstName("Sunil");
		customerEntity.setMiddleName("Krishna");
		customerEntity.setLastName("Behera");
		customerEntity.setGender(Gender.MALE);
		customerEntity.setStatus(Status.ENABLED);
		customerEntity.setIsExistingCustomer(Boolean.TRUE);
		customerEntity.setMaritalStatus(MaritalStatus.MARRIED);
		customerEntity.setCreatedBy("system");
		customerEntity.setVersion(1);
		customerRepository.save(customerEntity);
	}
	
}
