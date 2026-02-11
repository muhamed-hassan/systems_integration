package app.persistence.data;

import java.util.HashSet;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import app.persistence.entities.Employee;

@Component
public class InMemoryDataSourceGenerator {
	
	private HashSet<Employee> employees;
	
	@PostConstruct
	public void initDataSource() {	
		
		employees = new HashSet<Employee>();
		for (int counter = 1; counter <= 100; counter++) {
			
			Employee employee = new Employee();
			employee.setId(counter);
			employee.setName("name _ " + counter);
			employee.setTitle("title _ " + counter);		
			employees.add(employee);
		}		
	}

	public HashSet<Employee> getEmployees() {
		return employees;
	}
	
}
