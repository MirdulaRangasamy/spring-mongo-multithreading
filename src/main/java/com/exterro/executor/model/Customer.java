/**
 * @Placed com.exterro.executor
 */
package com.exterro.executor.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * @author mrangasamy
 *
 * @date 23-Jul-2024
 */
@Document(collection = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	@Id
	private int custId;
	private String cusName;
	private String email;
	private String mobile;
}
