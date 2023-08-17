package com.cg.api;

import com.cg.model.Customer;
import com.cg.model.Deposit;
import com.cg.model.LocationRegion;
import com.cg.model.dto.CustomerResDTO;
import com.cg.model.dto.LocationRegionResDTO;
import com.cg.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerApi {

    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {

        List<Customer> customers = customerService.findAll();

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResDTO> getById(@PathVariable Long id) {

        Optional<Customer> customerOptional = customerService.findById(id);

        if (customerOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Customer customer = customerOptional.get();

        CustomerResDTO customerResDTO = customer.toCustomerResDTO();

        return new ResponseEntity<>(customerResDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        Customer newCustomer = customerService.create(customer);

        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @PostMapping("/deposit")
    public ResponseEntity<Customer> deposit(@RequestBody Deposit deposit) {

        customerService.deposit(deposit);
        Optional<Customer> updateCustomer = customerService.findById(deposit.getCustomer().getId());

        return new ResponseEntity<>(updateCustomer.get(), HttpStatus.OK);
    }
}
