package com.nt.service;

import java.util.Optional;

import com.nt.entity.Employee;

public interface IEmployeeMgmtService {
	
	public  Iterable<Employee>  showAllEmployees();
	public   String   regsiterEmployee(Employee emp);
	public    Employee  fetchEmployeeById(int id);
	public    String    modifyEmployee(Employee emp);
	public  String    deleteEmployeeById(int id);

}
