package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

@Path("/todo")
public class TodoService {
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

		return result.toString();
	}
}
