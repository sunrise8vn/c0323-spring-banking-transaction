package com.cg.api;

import com.cg.exception.DataInputException;
import com.cg.model.Customer;
import com.cg.model.Deposit;
import com.cg.model.LocationRegion;
import com.cg.model.dto.CustomerResDTO;
import com.cg.model.dto.DepositReqDTO;
import com.cg.model.dto.LocationRegionResDTO;
import com.cg.service.customer.ICustomerService;
import com.cg.utils.ValidateUtils;
import com.mysql.cj.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.DataInput;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerApi {

    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        List<CustomerResDTO> customerResDTOS = customerService.findAllCustomerResDTO(false);

        if (customerResDTOS.size() == 0) {
            Map<String, String> result = new HashMap<>();
            result.put("message", "Không có khách hàng trong danh sách");

            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        return new ResponseEntity<>(customerResDTOS, HttpStatus.OK);
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
    public ResponseEntity<?> deposit(@RequestBody DepositReqDTO depositReqDTO) {

        if (depositReqDTO.getCustomerId() == null || depositReqDTO.getCustomerId().length() == 0) {
            throw new DataInputException("Vui lòng nhập mã khách hàng");
        }

        if (depositReqDTO.getTransactionAmount() == null || depositReqDTO.getTransactionAmount().length() == 0) {
            throw new DataInputException("Vui lòng nhập số tiền giao dịch");
        }

        if (!ValidateUtils.isNumberValid(depositReqDTO.getCustomerId())) {
            throw new DataInputException("Vui lòng nhập mã khách hàng bằng ký tự số");
        }

        if (!ValidateUtils.isNumberValid(depositReqDTO.getTransactionAmount())) {
            throw new DataInputException("Vui lòng nhập số tiền giao dịch bằng ký tự số");
        }

        Long customerId = Long.parseLong(depositReqDTO.getCustomerId());

        Customer customer = customerService.findById(customerId).orElseThrow(() -> {
            throw new DataInputException("Mã khách hàng không tồn tại");
        });

        BigDecimal transactionAmount = BigDecimal.valueOf(Long.parseLong(depositReqDTO.getTransactionAmount()));

        Deposit deposit = new Deposit();
        deposit.setCustomer(customer);
        deposit.setTransactionAmount(transactionAmount);

        customerService.deposit(deposit);
        Optional<Customer> updateCustomer = customerService.findById(deposit.getCustomer().getId());

        return new ResponseEntity<>(updateCustomer.get().toCustomerResDTO(), HttpStatus.OK);
    }
}
