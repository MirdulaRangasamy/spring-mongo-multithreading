/**
 * @Placed com.exterro.executor.service
 */
package com.exterro.executor.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.exterro.executor.model.Customer;
import com.exterro.executor.repository.CustomerRepository;

/**
 * @author mrangasamy
 *
 * @date 24-Jul-2024
 */
@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository custRepo;
	
	@Override
	public Customer addCustomer(Customer customer) {
		return custRepo.insert(customer);
	}

	@Override
	public Customer getCustomer(int custId) {
		return custRepo.findById(custId).get();
	}
	
	@Override
	public Customer getCustomer(String email) {
		return custRepo.findByEmail(email).get();
	}

	@Override
	@Async("asyncTask")
	public List<Customer> getAll() {
		return custRepo.findAll();
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		Customer cust = custRepo.findById(customer.getCustId()).get();
		cust.setCusName(customer.getCusName());
		cust.setEmail(customer.getEmail());
		cust.setMobile(customer.getMobile());
		return custRepo.save(cust);		
	}
	
	@Override
	public String deleteCustomer(int custId) {
		Optional<Customer> cust = custRepo.findById(custId);
		custRepo.deleteById(custId);
		return cust.isPresent()?"Success":"Failure";
	}

	@Override
	@Async("asyncTask")
	public ByteArrayInputStream downloadCustomer() {
		
			String[] HEADERs = { "Customer ID", "Customer Name", "EMail", "Mobile No" };
			  String SHEET = "Customer";
		    try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
		      Sheet sheet = workbook.createSheet(SHEET);

		      // Header
		      Row headerRow = sheet.createRow(0);

		      for (int col = 0; col < HEADERs.length; col++) {
		        Cell cell = headerRow.createCell(col);
		        cell.setCellValue(HEADERs[col]);
		      }

		      int rowIdx = 1;
		      for (Customer customer : getAll()) {
		        Row row = sheet.createRow(rowIdx++);

		        row.createCell(0).setCellValue(customer.getCustId());
		        row.createCell(1).setCellValue(customer.getCusName());
		        row.createCell(2).setCellValue(customer.getEmail());
		        row.createCell(3).setCellValue(customer.getMobile());
		      }

		      workbook.write(out);
		      return new ByteArrayInputStream(out.toByteArray());
		    } catch (IOException e) {
		      throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		    }
		  }

}
