package com.ericsson.charter.poc.client;


//import static org.junit.Assert.*;
//import java.util.List;

//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.Form;
//import javax.ws.rs.core.GenericType;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import javax.xml.bind.JAXBElement;

//import org.junit.Before;
//import org.junit.Test;

//import com.ericsson.charter.poc.bean.Title;

public class CatalogTest {

//	private WebTarget webTarget;
//	
//	@Before
//	public void setUp() throws Exception {
//		Client client = ClientBuilder.newClient();
//		webTarget = client.target("http://localhost:8080").path("jersey-catalog-tomcat/rest/titles");
//	}
//
//	@Test
//	public void getContacts() {
//		System.out.println("===== All Contacts =====");
//		
//		// 1, get response as plain text
//		String jsonRes = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
//		System.out.println(jsonRes);
//		
//		String xmlRes = webTarget.request(MediaType.APPLICATION_XML).get(String.class);
//		System.out.println(xmlRes);
//		
//		// 2, get response and headers etc, wrapped in ClientResponse
//		Response response = webTarget.request().get(Response.class);
//		System.out.println( response.getStatus() );
//		System.out.println( response.getHeaders().get("Content-Type") );
//		String entity = response.readEntity(String.class);
//		System.out.println(entity);
//		
//		// 3, get JAXB response
//		GenericType<List<Title>> genericType = new GenericType<List<Title>>() {};
//		List<Title> titles = webTarget.request(MediaType.APPLICATION_XML).get(genericType);
//		System.out.println("No. of Contacts: " + titles.size());
//		Title title = titles.get(0);
//		System.out.println(title.getId() + ": " + title.getName());
//	}
//	
//	@Test
//	public void getOneContact() {
//		System.out.println("===== Get 1 =====");
//		String id = "1";
//		
//		GenericType<JAXBElement<Title>> generic = new GenericType<JAXBElement<Title>>() {};
//		JAXBElement<Title> jaxbTitle = webTarget.path(id).request(MediaType.APPLICATION_XML).get(generic);
//		Title title = jaxbTitle.getValue();
//		System.out.println(title.getId() + ": " + title.getName());
//		assertTrue("Contact fetched", title.getId().intValue() == 1);
//	}
//	
//	@Test
//	public void postForm() {
//		System.out.println("===== Create Whiplash =====");
//		Form form = new Form();
//		form.param("name", "Whiplash");
//		form.param("year", "2014");
//		form.param("synopsis", "The road to greatness can take you to the edge.");
//		Response response = webTarget.request(MediaType.TEXT_HTML).post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE), Response.class);
//		System.out.println(response.readEntity(String.class));
//	}
//	
//	@Test
//	public void putOneContact() {
//		System.out.println("===== Create Paperman =====");
//		Title title = new Title("Paperman", 2012, "An urban office worker finds that paper airplanes are instrumental in meeting a girl in ways he never expected.");
//		
//		Response response = webTarget.path(title.getId().toString()).request(MediaType.APPLICATION_XML)
//								   .put(Entity.entity(title, MediaType.APPLICATION_XML_TYPE) ,Response.class);
//		System.out.println(response.getStatus());
//	}
//	
//	@Test
//	public void deleteOneContact() {
//		System.out.println("===== Delete 1 =====");
//		String id =  "1";
//		
//		Response response = webTarget.path(id).request().delete(Response.class);
//		System.out.println(response.getStatus());
//	}
}
