	package com.tpms.services;
	
	import java.util.ArrayList;
	import java.util.List;
	
	import org.springframework.stereotype.Service;
	
	import com.tpms.dao.CustomerRepository;
	import com.tpms.dto.CustomerDto;
	import com.tpms.entities.Customer;
	import com.tpms.exceptions.ResourceNotFoundException;
	
	@Service
	public class CustomerService {
	
		private final CustomerRepository customerRepository;
		
		public CustomerService(CustomerRepository customerRepository) {
			this.customerRepository = customerRepository;
		}
		
		//add details
		public CustomerDto addCustomer(Customer customer) {
			Customer newCustomer =  this.customerRepository.save(customer);
			return convertToDto(newCustomer);
		}
		
		//Fetch All
		public List<CustomerDto> getAllCustomerDto(){
			List<Customer> list = (List<Customer>)this.customerRepository.findAll();
			List<CustomerDto> customerDtoList = new ArrayList<>();
			
			for(Customer customer: list ) {
				customerDtoList.add(convertToDto(customer));
			}
			return customerDtoList;
		}
		
		//Fetch By Id
		public CustomerDto getCustomerDtoById(long id) {
			Customer customer =  this.customerRepository.findById(id)
					.orElseThrow(()-> 
					new ResourceNotFoundException("There is no Customer with ID: "+id));
			return convertToDto(customer);
		}
		
		//update by Id
		public CustomerDto updateCustomerById(long id, Customer customer) {
			Customer custom = this.customerRepository.findById(id)
					.orElseThrow(()-> new ResourceNotFoundException("There is no Customer with ID: "+id));
		
			//getters setters methods for updating data
			custom.setEmail(customer.getEmail());
			//add more setters and getters
			
			Customer updatedCustomer = this.customerRepository.save(custom);
			return convertToDto(updatedCustomer);
		}
		
		
		//Delete by ID
		public void deleteCustomerById(long id) {
			if( ! this.customerRepository.existsById(id)) {
				throw new ResourceNotFoundException("There is no Customer with ID: "+id);
			}
			
			this.customerRepository.deleteById(id);
		}
		
		
		public CustomerDto convertToDto(Customer customer) {
			CustomerDto customerDto = new CustomerDto();
			customerDto.setId(customer.getId());
			customerDto.setFirstName(customer.getFirstName());
			customerDto.setLastName(customer.getLastName());
			customerDto.setEmail(customer.getEmail());
			customerDto.setPhoneNumber(customer.getPhoneNumber());
			return customerDto;
		}
	}
