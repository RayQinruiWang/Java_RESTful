package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/todo")
public class TodoService {
	private static List<Todo> todos = new ArrayList<Todo>();
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public String sayHelloXML() {
		String response = "<?xml version='1.0'?> <hello>Hello XML</hello>";
		return response;
	}
	
/*	// response to GET, produces HTML
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String approveCC(@QueryParam("owner") String owner) {
		String temp = "";
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/to-do_users?useSSL=false","root","admin");
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery("select * from todos where owner = '" + owner +"'");
			todos.clear();
			
			while (myRs.next()) {
				temp = temp + "<h2>" + myRs.getString("description") + myRs.getString("category")+myRs.getString("owner") +"</h2></br>";
				todos.add(new Todo(myRs.getString("description"),
									myRs.getString("category"),
									myRs.getString("owner")));
			}
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		return "<html><h1>We've reached here: " + owner + "<h1></br>" + temp + "</html>";
	}
*/
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String approveCC(@QueryParam("owner") String owner) {
		return "{\"response\":\"This is test\", \"Another\": \"this is another\"}";
	}
}
