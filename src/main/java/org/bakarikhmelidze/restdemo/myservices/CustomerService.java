package org.bakarikhmelidze.restdemo.myservices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bakarikhmelidze.restdemo.model.Customer;
import org.bakarikhmelidze.restdemo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	   @Autowired
	   private CustomerRepository customerRepository;

	   public List<Customer> getAllCustomers() {

	       List<Customer> customers = new ArrayList<Customer>();
	       customerRepository.findAll().forEach(customers ::add);
	       return customers;
	   }


	   public Optional<Customer> getCustomerById(Long id) {
	            return  customerRepository.findById(id);
	   }

	   public void addCustomer(Customer customer) {
		   customerRepository.save(customer);
	   }

	   public void updateCustomer(long id, Customer customer) {
	       Optional<Customer> customerData = customerRepository.findById(id);

	       if (customerData.isPresent()) {
	           Customer _customer = customerData.get();
	           _customer.setEmail(customer.getEmail());
	           _customer.setFirstName(customer.getFirstName());
	           _customer.setLastName(customer.getLastName());
	           customerRepository.save(_customer);
	       }
	   }

	   public void deleteCustomer(long id) {
		   customerRepository.deleteById(id);
	   }

	   public void deleteAllCustomer() {
		   customerRepository.deleteAll();
	   }

	   

}
