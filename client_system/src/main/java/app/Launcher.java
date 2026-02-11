package app;

import app.integration.clients.EmployeeClient;

public class Launcher {

	public static void main(String[] args) {
		
		EmployeeClient employeeClient = new EmployeeClient();

		employeeClient.create();
		
		/* ******************************************************************************************************** */		
		employeeClient.getById();
		employeeClient.getPage();
				
		/* ******************************************************************************************************** */
		employeeClient.update();
		
		/* ******************************************************************************************************** */
		employeeClient.delete();

	}

}
