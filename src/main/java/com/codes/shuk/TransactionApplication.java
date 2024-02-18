package com.codes.shuk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PlaceholderConfigurerSupport;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.codes.shuk.dao.EmployeeDAO;
import com.codes.shuk.dto.Employee;

@SpringBootApplication
public class TransactionApplication implements  CommandLineRunner{
	@Autowired
	private IsolationTestServiceInvoker isolationTestServiceInvoker;

	public static void main(String[] args) {
		SpringApplication.run(TransactionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		isolationTestServiceInvoker.execute();
	}
}
