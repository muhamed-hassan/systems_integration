package app;

import app.integration.clients.BackendSystemClient;

public class Launcher {

	public static void main(String[] args) {
		
		BackendSystemClient backendSystemClient = new BackendSystemClient();

		backendSystemClient.save();
		
		/* ******************************************************************************************************** */		
		backendSystemClient.getById();
		backendSystemClient.getPage();
				
		/* ******************************************************************************************************** */
		backendSystemClient.update();
		
		/* ******************************************************************************************************** */
		backendSystemClient.delete();

	}

}
