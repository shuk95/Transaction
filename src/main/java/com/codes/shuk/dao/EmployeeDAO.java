package com.codes.shuk.dao;

import java.util.List;

import javax.sound.midi.VoiceStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.codes.shuk.dto.Employee;

@Repository
public class EmployeeDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public List<Employee> readAllEmployees() {
		String sqlString = "select  employee_id as id, first_name as firstName , last_name as lastName, email as email, salary as salary from employee";
		List<Employee> listOfEmployees = jdbcTemplate.query(sqlString, new BeanPropertyRowMapper<Employee>(Employee.class)); //fetching above data and binding it to the entity class
		return listOfEmployees;	
	}
	
	public void saveEmployee(Employee employee) {
		Object[] newEmployee ={employee.getId(),employee.getFirstName(),employee.getLastName(),employee.getEmail(),employee.getSalary()	};
		String queryString = "insert into employee(employee_id, first_name, last_name, email, salary) values(?,?,?,?,?)";
		try{
			int isUpdated = jdbcTemplate.update(queryString, newEmployee);
			if(isUpdated==1) {
				System.out.println("Employee added is "+employee);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void updateEmployee(int id){
		String query = "update employee set email ='sush@gmail.com' where employee_id =?";
		try {
			int update = jdbcTemplate.update(query, id);
			if(update==1){
				System.out.println("Employee email update with id "+id);
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
