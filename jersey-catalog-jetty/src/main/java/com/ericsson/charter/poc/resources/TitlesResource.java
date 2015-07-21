package com.ericsson.charter.poc.resources;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import com.ericsson.charter.poc.bean.Title;
import com.ericsson.charter.poc.storage.TitleStore;
import com.ericsson.charter.poc.util.ParamUtil;


@Path("/titles")
public class TitlesResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Title> getContacts() {
		List<Title> contacts = new ArrayList<Title>();
		contacts.addAll( TitleStore.getStore().values() );
		return contacts;
	}
	
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = TitleStore.getStore().size();
		return String.valueOf(count);
	}
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newTitle(
			@FormParam("name") String name,
			@FormParam("year") Integer year,
			@FormParam("synopsis") String synopsis,
			@Context HttpServletResponse servletResponse
	) throws IOException {
		Title c = new Title(name,year,synopsis);
		TitleStore.getStore().put(c.getId(), c);
		
		URI uri = uriInfo.getAbsolutePathBuilder().path(c.getId().toString()).build();
		Response.created(uri).build();
		
		servletResponse.sendRedirect("../pages/new_contact.html");
	}
	
	
	@GET
	@Path("{title}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Title getContact(
			@PathParam("title") Integer titleId) {
		Title cont = TitleStore.getStore().get(titleId);
		if(cont==null)
			throw new NotFoundException("No such Title.");
		return cont;
	}
	
	@PUT
	@Path("{title}")
	@Consumes(MediaType.APPLICATION_XML)
	public Response putContact(JAXBElement<Title> jaxbContact) {
		Title c = jaxbContact.getValue();
		return putAndGetResponse(c);
	}
	
	@PUT
	@Path("{title}")
	public Response putContact(@Context HttpHeaders herders, byte[] in) {
		Map<String,String> params = ParamUtil.parse(new String(in));
		Title c = new Title(params.get("name"), Integer.parseInt(params.get("year")), params.get("synopsis"));
		return putAndGetResponse(c);
	}
	
	private Response putAndGetResponse(Title c) {
		Response res;
		if(TitleStore.getStore().containsKey(c.getId())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		TitleStore.getStore().put(c.getId(), c);
		return res;
	}
	
	@DELETE
	@Path("{title}")
	public void deleteContact(
			@PathParam("title") Integer titleId) {
		Title c = TitleStore.getStore().remove(titleId);
		if(c==null)
			throw new NotFoundException("No such Title.");
	}
	
}
