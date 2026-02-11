package app.integration.clients;

import com.lib.integration.HttpClient;

import app.integration.models.EmployeeUpdateModel;
import app.integration.models.NewEmployeeModel;
import app.integration.models.PageModel;
import app.integration.models.SavedEmployeeModel;

public class BackendSystemClient extends HttpClient {
	
	public BackendSystemClient() {
		super("http://localhost:8080/");
	}
	
	public void save() {				
		NewEmployeeModel employee = new NewEmployeeModel();
		employee.setName("sample name");
		employee.setTitle("sample title");
		post("employees", employee);
	}
	
	/* ******************************************************************************************************** */
	public SavedEmployeeModel getById() {
		return (SavedEmployeeModel) get("employees/1", SavedEmployeeModel.class);
	}	
	
	public PageModel getPage() {
		return (PageModel) get("employees?pageIndex=0", PageModel.class);
	}
	
	/* ******************************************************************************************************** */	
	public void update() {
		EmployeeUpdateModel employee = new EmployeeUpdateModel();
		employee.setTitle("updated sample title");
		put("employees/91", employee);
	}
	
	/* ******************************************************************************************************** */	
	public void delete() {
		delete("employees/51");
	}

}
