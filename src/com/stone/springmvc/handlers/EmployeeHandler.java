package com.stone.springmvc.handlers;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.stone.springmvc.dao.DepartmentDao;
import com.stone.springmvc.dao.EmployeeDao;
import com.stone.springmvc.entities.Employee;

@Controller
public class EmployeeHandler {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	@ModelAttribute
	public void getEmployee(@RequestParam(value="id",required=false) Integer id,
			Map<String, Object> map) {
		if (id != null) {
			map.put("employee", employeeDao.get(id));
		}
	}
	
	@RequestMapping(value="/emp", method=RequestMethod.PUT)
	public String update(Employee employee) {
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	
	@RequestMapping(value="/emp/{id}", method=RequestMethod.GET)
	public String input(@PathVariable("id") Integer id, Map<String, Object> map) {
		//创建要修改的bean实例，其名称与表单的modelAttribute一致
		map.put("employee", employeeDao.get(id));
		map.put("departments", departmentDao.getDepartments());
		return "input";
	}
	
	@RequestMapping(value="/emp/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id){
		employeeDao.delete(id);
		return "redirect:/emps";
	}
	
	@RequestMapping(value="/emp", method=RequestMethod.POST)
	public String save(@Valid Employee employee, BindingResult result, Map<String, Object> map) {
		//需校验的Bean对象(employee)和其绑定结果对象或错误对象(result)时成对出现的，它们之间不允许声明其他的入参
		System.out.println("save: " + employee);
		
		if (result.getErrorCount() > 0) {
			System.out.println("出错了！");
			List<FieldError> errors = result.getFieldErrors();
			for(FieldError error:errors) {
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			
			//若验证出错，则转向定制的页面
			map.put("departments", departmentDao.getDepartments());
			return "input";
		}
		
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	
	@RequestMapping(value="/emp", method=RequestMethod.GET)
	public String input(Map<String, Object> map) {
		//表单上要显示部门信息
		map.put("departments", departmentDao.getDepartments());
		//创建要录入到表单中的bean实例，其名称与表单的modelAttribute一致
		map.put("employee", new Employee());
		return "input";
	}
	
	@RequestMapping("/emps")
	public String list(Map<String, Object> map) {
		map.put("employees", employeeDao.getAll());
		return "list";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setDisallowedFields("lastName");
	}
}
