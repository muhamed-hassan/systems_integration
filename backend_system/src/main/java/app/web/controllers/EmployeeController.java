package app.web.controllers;

import java.util.HashSet;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.persistence.entities.Employee;
import app.persistence.entities.Page;
import app.persistence.repositories.EmployeeRepository;
import app.web.models.EmployeeUpdateModel;
import app.web.models.NewEmployeeModel;
import app.web.models.PageModel;
import app.web.models.SavedEmployeeModel;

@RestController
@RequestMapping("employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody NewEmployeeModel newEmployee) {
		
		Employee employee = new Employee();
		employee.setName(newEmployee.getName());
		employee.setTitle(newEmployee.getTitle());	
		
		employeeRepository.save(employee);		
    	
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}	
	
	/* ******************************************************************************************************** */		
	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public ResponseEntity<SavedEmployeeModel> getById(@PathVariable int id) {
		
		Employee employee = employeeRepository.findById(id);
		
		SavedEmployeeModel savedEmployee = new SavedEmployeeModel();
		savedEmployee.setId(id);
		savedEmployee.setName(employee.getName());
		savedEmployee.setTitle(employee.getTitle());		
		
		return new ResponseEntity<SavedEmployeeModel>(savedEmployee, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<PageModel> getPage(@RequestParam int pageIndex) {
		
		Page page = employeeRepository.findPage(pageIndex);
		
		HashSet<SavedEmployeeModel> collectedElements = new HashSet<SavedEmployeeModel>();
		Iterator<Employee> iterator = page.getData().iterator();		
		while (iterator.hasNext()) {
			
			Employee currentElement = iterator.next();
			
			SavedEmployeeModel savedEmployee = new SavedEmployeeModel();
			savedEmployee.setId(currentElement.getId());
			savedEmployee.setName(currentElement.getName());
			savedEmployee.setTitle(currentElement.getTitle());
			
			collectedElements.add(savedEmployee);
		}		
		
		PageModel pageModel = new PageModel();
		pageModel.setData(collectedElements);
		pageModel.setFirstPage(page.isFirstPage());
		pageModel.setLastPage(page.isLastPage());
		
		return new ResponseEntity<PageModel>(pageModel, HttpStatus.OK);
	}
	
	/* ******************************************************************************************************** */		
	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public ResponseEntity<Object> update(@PathVariable int id, @RequestBody EmployeeUpdateModel employeeUpdateModel) {
		
		employeeRepository.update(id, employeeUpdateModel);
		
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	/* ******************************************************************************************************** */
	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public ResponseEntity<Object> delete(@PathVariable int id) {
		
		employeeRepository.delete(id);
		
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

}
