package customer.customer;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
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


@RestController
@RequestMapping(path =  "/api/", produces = {APPLICATION_JSON_VALUE})
public class CustomerController {

    private static final String SUBSTRING = "subString";

    private static final String ID = "id";

    @Autowired
    CustomerService customerService;

    @ResponseStatus(CREATED)
    @PostMapping
    public CustomerEntity createAccount(@Validated(CustomerCreate.class) @RequestBody CustomerEntity account) {

        return customerService.save(account);
    }

    @ResponseStatus(OK)
    @PutMapping
    public CustomerEntity updateAccount(@Validated(CustomerUpdate.class) @RequestBody CustomerEntity account) {

        return customerService.save(account);
    }

    @GetMapping(value = {"/search/{subString}"})
    public List<CustomerEntity> searchCustomerByName(
            @PathVariable(value = SUBSTRING, required = false) Optional<String> subString) throws Exception {
        String searchString = subString.orElse("");
        return customerService.searchCustomersByName(searchString);
    }

    @GetMapping(value = "/customer/{id}")
    public CustomerEntity getCustomerById(@PathVariable(ID) Long id) {
        return customerService.getCustomerById(id);
    }
}
