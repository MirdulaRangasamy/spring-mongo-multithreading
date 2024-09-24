/**
 * @Placed com.exterro.executor.controller
 */
package com.exterro.executor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exterro.executor.model.Customer;
import com.exterro.executor.service.CustomerService;

/**
 * @author mrangasamy
 *
 * @date 24-Jul-2024
 */
@RestController
@RequestMapping("customers")
public class CustomerController {
	
	@Autowired
	private CustomerService custSvc;
	
	@PostMapping("addCustomer")
	public Customer addCustomer(@RequestBody Customer customer) {
		return custSvc.addCustomer(customer);
	}
	
	@GetMapping("id/{custId}")
	public Customer getCustomer(@PathVariable int custId) {
		return custSvc.getCustomer(custId);
	}
	
	@GetMapping("email/{email}")
	public Customer getCustomer(@PathVariable String email) {
		return custSvc.getCustomer(email);
	}
	
	@GetMapping("")
	public List<Customer> findAll(){
		return custSvc.getAll();
	}
	
	@PutMapping("updateCustomer")
	public Customer updateCustomer(@RequestBody Customer customerDto) {
		return custSvc.addCustomer(customerDto);
	}
	
	@DeleteMapping("{custId}")
	public String deleteCustomer(@PathVariable int custId) {
		return custSvc.deleteCustomer(custId);
	}
	
	@GetMapping("downloadCustomer")
	public String downloadFiles() {
		return "";
	}
}
