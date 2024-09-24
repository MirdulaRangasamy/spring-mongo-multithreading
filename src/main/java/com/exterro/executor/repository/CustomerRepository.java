/**
 * @Placed com.exterro.executor.repository
 */
package com.exterro.executor.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.exterro.executor.model.Customer;

/**
 * @author mrangasamy
 *
 * @date 24-Jul-2024
 */
public interface CustomerRepository extends MongoRepository<Customer, Integer>{
	Optional<Customer> findByEmail(String email);
}
