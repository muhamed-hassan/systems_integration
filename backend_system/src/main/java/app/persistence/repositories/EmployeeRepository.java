package app.persistence.repositories;

import java.util.Iterator;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import app.persistence.data.InMemoryDataSourceGenerator;
import app.persistence.entities.Employee;
import app.persistence.entities.Page;
import app.persistence.repositories.exceptions.DataNotFoundException;
import app.web.models.EmployeeUpdateModel;

@Component
public class EmployeeRepository {
	
	@Autowired
	private InMemoryDataSourceGenerator dataSource;
	
	@Value("${page.size}")
    private int pageSize;
		
	public int save(Employee employee) {
		
		HashSet<Employee> employees = dataSource.getEmployees();
		Iterator<Employee> iterator = employees.iterator();	
		int maxId = 0;
		while (iterator.hasNext()) {
			
			Employee currentElement = iterator.next();
			if (maxId < currentElement.getId()) {
				maxId = currentElement.getId();
			}
		}		
		
		int idOfCreatedEmployee = maxId + 1;		
		employee.setId(idOfCreatedEmployee);		
		employees.add(employee);
		
		return idOfCreatedEmployee;
	}	

	public Employee findById(int id) {	
				
		Employee foundEmployee = null;		
		Iterator<Employee> iterator = dataSource.getEmployees().iterator();		
		while (iterator.hasNext()) {
			
			Employee currentElement = iterator.next();
			if (currentElement.getId() == id) {
				foundEmployee = currentElement;
				break;
			}
		}
		
		if (foundEmployee == null) {
			throw new DataNotFoundException("Employee with id " + id + " is not found!");
		}
		
		return foundEmployee;
	}
	
	public Page findPage(int pageIndex) {
		
		if (pageIndex < 0) {
			throw new IllegalArgumentException("pageIndex should be a positive value");
		}
		
		HashSet<Employee> employees = dataSource.getEmployees();
		int totalElements = employees.size();				
		int totalPages = (int) Math.ceil((totalElements * 1.0) / pageSize);
		
		if (pageIndex > totalPages) {
			throw new IllegalArgumentException("pageIndex is out of range");
		}			
		
		HashSet<Employee> collectedElements = new HashSet<Employee>();		
		Iterator<Employee> iterator = employees.iterator();		
		int skippedElements = pageIndex * pageSize;
		int collectedElementsLimit = 1; // max is pageSize
		int cursor = 0;
		while (iterator.hasNext()) {
			
			Employee currentElement = iterator.next();
			cursor++;			
			if (cursor > skippedElements) {
				
				if (collectedElementsLimit <= pageSize) {
					
					collectedElements.add(currentElement);
					collectedElementsLimit++;					
				}				
			}
		}
		
		boolean isFirstPage = (pageIndex == 0);
		boolean isLastPage = ((pageIndex + 1) == totalPages);
		
		Page page = new Page();
		page.setData(collectedElements);
		page.setFirstPage(isFirstPage);
		page.setLastPage(isLastPage);

		return page;
	}	
	
	public void update(int id, EmployeeUpdateModel employeeUpdateModel) {
		
		Employee oldEmployee = findById(id);
		
		// update using a pointer to the old object in memory
		oldEmployee.setTitle(employeeUpdateModel.getTitle());
	}
	
	public void delete(int id) {
		
		Employee employee = findById(id);		
		dataSource.getEmployees().remove(employee);
	}
	
}
