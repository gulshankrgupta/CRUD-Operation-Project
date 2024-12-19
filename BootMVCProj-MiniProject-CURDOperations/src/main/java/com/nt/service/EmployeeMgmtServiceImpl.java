package com.nt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.Employee;
import com.nt.repository.IEmployeeRepository;

@Service("empService")
public class EmployeeMgmtServiceImpl implements IEmployeeMgmtService {
	@Autowired
	private IEmployeeRepository empRepo;

	@Override
	public Iterable<Employee> showAllEmployees() {
		
		return empRepo.findAll();
	}

	@Override
	public String regsiterEmployee(Employee emp) {
		//use repo
		int idVal=empRepo.save(emp).getEmpno();
		return "employee record is inserted with the id value :;"+idVal;
	}

	@Override
	public Employee fetchEmployeeById(int id) {
		return   empRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid number"));
	}
	
	@Override
	public String modifyEmployee(Employee emp) {
		  Optional<Employee> opt=empRepo.findById(emp.getEmpno());
		  if(opt.isPresent()) {
			  empRepo.save(emp);
			  return emp.getEmpno()+" is updated";
		  }
		return emp.getEmpno()+" is not found for updation";
	}
	
	@Override
	public String deleteEmployeeById(int id) {
		  Optional<Employee> opt=empRepo.findById(id);
		  if(opt.isPresent()) {
			  empRepo.deleteById(id);
			  return id+"  is Deleted";
		  }
		return id+" is not found for Deletion";
	}

}
