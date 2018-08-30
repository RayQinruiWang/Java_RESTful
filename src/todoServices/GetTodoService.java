package todoServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

@Path("/gettodo")
public class GetTodoService {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String retrieveToDo(@QueryParam("owner") String owner) {
		JSONObject result = new JSONObject();
		
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/to-do_users?useSSL=false","root","admin");
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery("select * from todos where owner = '" + owner +"'");
			
			JSONArray todos = new JSONArray();
			
			while (myRs.next()) {
				// construct the JSON object for each todo's
				JSONObject todo = new JSONObject();
				todo.put("description", myRs.getString("description"));
				todo.put("category", myRs.getString("category"));
				todos.put(todo);
			}
			
			result.put("owner", owner);
			result.put("todos", todos);
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}

		return result.toString();
	}
	
	// Add a delete/post request to delete to-do from API
}
