package com.tpms.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tpms.dao.CustomerRepository;
import com.tpms.entities.Customer;
import com.tpms.exceptions.ResourceNotFoundException;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;
	
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	//add details
	public Customer addCustomer(Customer customer) {
		return this.customerRepository.save(customer);
	}
	
	//Fetch All
	public List<Customer> getAllCustomer(){
		List<Customer> list = (List<Customer>)this.customerRepository.findAll();
		return list;
	}
	
	//Fetch By Id
	public Customer getCustomerById(long id) {
		return this.customerRepository.findById(id)
				.orElseThrow(()-> 
				new ResourceNotFoundException("There is no Customer with ID: "+id));
	}
	
	//update by Id
	public Customer updateCustomerById(long id, Customer customer) {
		Customer custom = this.customerRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("There is no Customer with ID: "+id));
	
		//getters setters methods for updating data
		custom.setEmail(customer.getEmail());
		//add more setters and getters
		
		return this.customerRepository.save(custom);
	}
	
	
	//Delete by ID
	public void deleteCustomerById(long id) {
		if( ! this.customerRepository.existsById(id)) {
			throw new ResourceNotFoundException("There is no Customer with ID: "+id);
		}
		
		this.customerRepository.deleteById(id);
	}
	
}
