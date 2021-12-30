package org.sygnific.readers.resource;

import java.util.List;

import org.sygnific.readers.model.UserHistory;
import org.sygnific.readers.model.Users;
import org.sygnific.readers.service.UserService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
	
	UserService service = new UserService();
	
	@GET
	public List<Users> getAllUsers() {
		return service.getAllUsers();
	}
	
	@GET
	@Path("/{id}")
	public Users getUserById(@PathParam("userId") int userId) {
		return service.getUserById(userId);
	}
	
	@POST
	public Response addusers(Users user) {
		if(service.addUsers(user)) {
			return Response.status(Status.CREATED).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	@DELETE
	@Path("/{userid}")
	public Response removeUsers(@PathParam("userId") int userId) {
		if(service.removerUsers(userId)) {
			return Response.status(Status.ACCEPTED).build();
		}
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@GET
	@Path("/{userid}")
	public List<UserHistory> userHistory(@PathParam("userId") int userId) {
		return service.getUserHistory(userId);
	}
	
}
