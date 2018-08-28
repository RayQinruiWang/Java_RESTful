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

import org.json.JSONException;
import org.json.JSONObject;

@Path("/todo")
public class TodoService {
	private static List<Todo> todos = new ArrayList<Todo>();
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public String sayHelloXML() {
		String response = "<?xml version='1.0'?> <hello>Hello XML</hello>";
		return response;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String approveCC(@QueryParam("owner") String owner) {
		JSONObject result = new JSONObject();
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/to-do_users?useSSL=false","root","admin");
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery("select * from todos where owner = '" + owner +"'");
			
			while (myRs.next()) {
				result.put(myRs.getString("description"), myRs.getString("category"));
			}
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		
		System.out.println(result.toString());
		return result.toString();
	}
}
