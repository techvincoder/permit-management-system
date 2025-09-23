package com.tpms.controllers.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpms.entities.Customer;
import com.tpms.services.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	//Get all Details
	@GetMapping("/")
	public ResponseEntity<List<Customer>> getAllCusomers(){
		List<Customer> customers = this.customerService.getAllCustomer();
		return ResponseEntity.ok(customers);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable Long id){
		Customer customer = this.customerService.getCustomerById(id);
		return ResponseEntity.ok(customer);
	}
	
	@PostMapping("/")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
		Customer newCustomer = this.customerService.addCustomer(customer);
		return ResponseEntity.status(HttpStatus.CREATED).body(newCustomer);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Long id , @RequestBody Customer customer){
		Customer newCustomer = this.customerService.getCustomerById(id);
		return ResponseEntity.ok(newCustomer);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
		this.customerService.deleteCustomerById(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
}
