package org.bakarikhmelidze.restdemo.controller;

import java.util.List;
import java.util.Optional;

import org.bakarikhmelidze.restdemo.model.Customer;
import org.bakarikhmelidze.restdemo.myservices.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class CustomerController {
	
  @Autowired
  private CustomerService customerServices;      
  @GetMapping("/api")
  public List<Customer> getAllCustomers(@RequestParam(required = false) String title)         {
      return customerServices.getAllCustomers();
     
  }

  @GetMapping("/customers/{id}")
  public Optional<Customer> getCustomerById(@PathVariable("id") long id) {
   /* the TutorialRepository provides a method findById(). This methods takes the id of the
   Tutorial to find. This method used to be findOne(). But since Spring data jpa 2.0 it's changed to findById().
    */
      return  customerServices.getCustomerById(id);
  }
 /*  To add new Tutorials is really easy. You do this by using the TutorialRepository save() method.
  */
  @PostMapping("/customers")
  public void createCustomer(@RequestBody Customer customer) {
	  customerServices.addCustomer(customer);
  }

//To update a tutorial record, we used the same save() and findById()
  @PutMapping("/customers/{id}")
  public void updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
      Optional<Customer> customerData = customerServices.getCustomerById(id);

      if (customerData.isPresent()) {
          Customer _customer = customerData.get();
          _customer.setEmail(customer.getEmail());
          _customer.setFirstName(customer.getFirstName());
          _customer.setLastName(customer.getLastName());
          customerServices.addCustomer(_customer);
      }
  }

  @DeleteMapping("/customers/{id}")
  public void deleteCustomer(@PathVariable("id") long id) {
	  customerServices.deleteCustomer(id);
  }
 /* To delete a tutorials record, you simply use the deleteById() method provided by the tutorialRepository.
  Then you pass in the id of the record you want to delete.
  */
  @DeleteMapping("/customers")
  public void deleteAllCustomers() {

	  customerServices.deleteAllCustomer();
  }




}
