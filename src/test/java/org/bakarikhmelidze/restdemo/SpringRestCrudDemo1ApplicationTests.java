package org.bakarikhmelidze.restdemo;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.bakarikhmelidze.restdemo.model.Customer;
import org.bakarikhmelidze.restdemo.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;


@DataJpaTest //this class will be running as JPA test
@AutoConfigureTestDatabase(replace = Replace.NONE) //to use real database not in memory database
@Rollback(false)
class SpringRestCrudDemo1ApplicationTests {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	void contextLoads() {
		String email = "JohnDoe@gmail.com";
		   
	    Optional<Customer> c = customerRepository.findById((long) 5);
	   
	    Customer cust = c.get();
		assertThat(cust.getEmail()).isEqualTo(email);
	}

}
