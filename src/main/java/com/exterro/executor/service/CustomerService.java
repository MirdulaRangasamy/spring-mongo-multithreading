/**
 * @Placed com.exterro.executor.service
 */
package com.exterro.executor.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.exterro.executor.model.Customer;

/**
 * @author mrangasamy
 *
 * @date 24-Jul-2024
 */
@Service
public interface CustomerService {
	Customer addCustomer(Customer customer); 
	Customer getCustomer(int custId);
	Customer getCustomer(String email);
	List<Customer> getAll();
	ByteArrayInputStream downloadCustomer();
	Customer updateCustomer(Customer customer);
	String deleteCustomer(int custId);
}
