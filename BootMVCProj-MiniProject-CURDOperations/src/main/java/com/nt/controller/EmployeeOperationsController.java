package com.nt.controller;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.entity.Employee;
import com.nt.service.IEmployeeMgmtService;

@Controller
public class EmployeeOperationsController {
	@Autowired
	private IEmployeeMgmtService empService;
	
	@GetMapping("/")
	public  String  showHomePage() {
		//return LVN
		return "welcome";
	}
	
	@GetMapping("/report")
	public  String   fetchAllEmployees(Map<String,Object> map) {
		System.out.println("EmployeeOperationsController.fetchAllEmployees()");
		//use  service
		Iterable<Employee> it=empService.showAllEmployees();
		//keep the results in Model attribute
		map.put("empsList", it);
		//retrun LVN
		return "show_report";
	}
	
	@GetMapping("/register")   //for  form launching
	public  String   showRegisterEmployeeFormPage(@ModelAttribute("emp") Employee emp) {
		//return lvn (form page  for employee registration)
		return  "employee_register_form";
	}
	
	/*@PostMapping("/register")   //for  form submission
	public  String   registerEmployee(@ModelAttribute("emp")Employee emp,
			                                                       Map<String,Object> map) {
		//use  service
		String msg=empService.regsiterEmployee(emp);
		Iterable<Employee> list=empService.showAllEmployees();
		// keep the  results in shared memory
		map.put("resultMsg",msg);
		map.put("empsList", list);
		//return  LVN (report page)
		return "show_report";
	}
	*/
	
	/*@PostMapping("/register")   //for  form submission   with PRG Pattern
	public  String   registerEmployee(@ModelAttribute("emp")Employee emp,
			                                                       Map<String,Object> map) {
		System.out.println("EmployeeOperationsController.registerEmployee()");
		
		//use  service
		String msg=empService.regsiterEmployee(emp);
		// keep the  results in shared memory
		map.put("resultMsg",msg);
		//return  LVN (report page)
		return "redirect:report";   //PRG pattern support
	}*/
	
	@PostMapping("/register")   //for  form submission   with PRG Pattern  with  Flash Attributes
	public  String   registerEmployee(@ModelAttribute("emp")Employee emp,
			                                                       RedirectAttributes  map) {
		System.out.println("EmployeeOperationsController.registerEmployee()");
		
		//use  service
		String msg=empService.regsiterEmployee(emp);
		// keep the  results in shared memory
		map.addFlashAttribute("resultMsg",msg);
		//return  LVN (report page)
		return "redirect:report";   //PRG pattern support
	}
	
	/*	@PostMapping("/register")   //for  form submission   with PRG Pattern  with  Session Attributes
		public  String   registerEmployee(@ModelAttribute("emp")Employee emp,
				                                                       HttpSession  ses) {
			System.out.println("EmployeeOperationsController.registerEmployee()");
			
			//use  service
			String msg=empService.regsiterEmployee(emp);
			// keep the  results in shared memory
			ses.setAttribute("resultMsg",msg);
			//return  LVN (report page)
			return "redirect:report";   //PRG pattern support
		}
	*/
	
	@GetMapping("/edit")   //for  form launching
	public String  showEditFormPage(@RequestParam("id") int id,
			                                                         @ModelAttribute("emp") Employee emp) {
		// get  Employee record by  submitting the id
		   Employee emp1=empService.fetchEmployeeById(id);
		   //copy  emp1 obj data to   emp obj
		   BeanUtils.copyProperties(emp1, emp);
		   //return LVN  (edit form page)
		   return "employee_edit_form";
	}
	
	@PostMapping("/edit")
	public  String editEmployee(@ModelAttribute("emp") Employee emp,
			                                               RedirectAttributes  attrs) {
		//use  service
		String msg=empService.modifyEmployee(emp);
		//keep the results in  flash attributes
		attrs.addFlashAttribute("resultMsg", msg);
		//return  LVN
		return "redirect:report";
		
	}
	
	@GetMapping("/delete")
	public   String    deleteEmployee(@RequestParam("id") int id,
			                                                        RedirectAttributes  attrs) {
		//use service
		String msg=empService.deleteEmployeeById(id);
		//keep the results in flash attributes
		attrs.addFlashAttribute("resultMsg", msg);
		//redirect request
		return "redirect:report";
	}
	
	
}
