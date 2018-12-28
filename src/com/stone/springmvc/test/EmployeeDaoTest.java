package com.stone.springmvc.test;

import java.util.Iterator;

import org.junit.Test;

import com.stone.springmvc.dao.EmployeeDao;
import com.stone.springmvc.entities.Employee;

public class EmployeeDaoTest {

	@Test
	public void test() {
		EmployeeDao employeeDao = new EmployeeDao();
		Iterator<Employee> iterator = employeeDao.getAll().iterator();
		while (iterator.hasNext()) {
			Employee employee = (Employee) iterator.next();
			System.out.println(employee);
		}
		
	}

}
