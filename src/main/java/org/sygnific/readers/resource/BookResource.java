package org.sygnific.readers.resource;

import java.util.List;

import org.sygnific.readers.model.BeamModel;
import org.sygnific.readers.model.Book;
import org.sygnific.readers.service.BookService;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {
	
	BookService service = new BookService();
	
	@GET
	public List<String> getAllBooks(@BeanParam BeamModel model){
		if(model.getAuthor()!=null) {
			return service.getBooksByAuthor(model.getAuthor());
		}
		if(model.getGenre()!=null) {
			return service.getBooksByGenre(model.getGenre());
		}
		if(model.getName()!=null) {
			return service.getBooksByName(model.getName());
		}
		if(model.getId()!=null) {
			return service.getBooksById(model.getId());
		}
		return service.getAllBooks();
	}
		
	@DELETE
	@Path("/{id}")
	public Response RemoveBooks(@PathParam("id") int id) {
		if(service.removeBooks(id)) {
			return Response.status(Status.OK).build();
		}
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@PUT
	@Path("/{id}")
	public Response updateBooks(@PathParam("id") int id , Book book) {
		if (service.updateBooks(id,book)) {
			return Response.status(Status.ACCEPTED).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@POST
	public Response addBooks(Book book) {
		Book books =  service.addBook(book);
		return Response.status(Status.ACCEPTED).entity(books).build();
	}
	
	@POST
	@Path("/{userId}/{bookId}")
	public Response assignBooksToUser(@PathParam("userId") int userId, @PathParam("bookId") int bookId) {
		if (service.assignBook(userId, bookId)) {
			return Response.status(Status.ACCEPTED).build();
		}
		return Response.status(Status.CONFLICT).build();
	}
	
}
