package com.codes.shuk.service;

import com.codes.shuk.dao.EmployeeDAO;
import com.codes.shuk.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public abstract class AbstractIsolationTestService {

    @Autowired
    private EmployeeDAO employeeDAO;

    public CountDownLatch firstLatch = new CountDownLatch(1);
    public CountDownLatch secondLatch = new CountDownLatch(1);
    public CountDownLatch thirdLatch = new CountDownLatch(1);

    public CountDownLatch getFourthLatch() {
        return fourthLatch;
    }

    public void setFourthLatch(CountDownLatch fourthLatch) {
        this.fourthLatch = fourthLatch;
    }

    public CountDownLatch fourthLatch = new CountDownLatch(1);
    private CountDownLatch readWriteLatch = new CountDownLatch(2);
    public void readOperation(){
        List<Employee> allEmployees = employeeDAO.readAllEmployees();
        allEmployees.forEach(e-> System.out.println(e));

    }

    public void writeOperation(){
        Employee employee = new Employee();
        employee.setId(3);
        employee.setFirstName("Somesh");
        employee.setLastName("Shukla");
        employee.setEmail("som@gmail.com");
        employee.setSalary(33);

        employeeDAO.saveEmployee(employee);
        employeeDAO.updateEmployee(1);
    }
    public CountDownLatch getReadWriteLatch() {
        return readWriteLatch;
    }
    public void setReadWriteLatch(CountDownLatch readWriteLatch) {
        this.readWriteLatch = readWriteLatch;
    }

}
