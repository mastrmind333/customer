package customer.customer;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService {
	
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

    CustomerRepository customerRepository;

    public CustomerEntity save(CustomerEntity customer) {
        return customerRepository.save(customer);
    }

    public List<CustomerEntity> searchCustomersByName(String subString) {
    	return null;
        //return customerRepository.findAllByName(subString).collect(toList());
    }

    public CustomerEntity getCustomerById(Long id) {
        return customerRepository.findById(id).get();
    }
}
