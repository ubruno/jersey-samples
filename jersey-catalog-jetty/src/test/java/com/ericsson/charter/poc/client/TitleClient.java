package com.ericsson.charter.poc.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;

import com.ericsson.charter.poc.bean.Title;

public class TitleClient {
	
	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080").path("jersey-catalog-tomcat/rest/titles");
		//WebResource r = c.resource("http://localhost:8080/Jersey/rest/contacts");
		
		System.out.println("===== Get 1 =====");
		getOneContact(target, "1");
		
		System.out.println("===== Create Whiplash =====");
		postForm(target, "Whiplash", "2014", "The road to greatness can take you to the edge.");
		
		Title cnt = new Title("Paperman", 2012, "An urban office worker finds that paper airplanes are instrumental in meeting a girl in ways he never expected.");
		
		System.out.println("===== Create Paperman =====");
		putOneContact(target, cnt);
		
		System.out.println("===== All Contacts =====");
		getContacts(target);
		
		System.out.println("===== Delete 1 =====");
		deleteOneContact(target, "1");
		
		System.out.println("===== All Contacts =====");
		getContacts(target);
	}
	
	//public static void getContacts(WebResource r) {
	public static void getContacts(WebTarget r) {
		
		// 1, get response as plain text
		String jsonRes = r.request(MediaType.APPLICATION_JSON).get(String.class);
		System.out.println(jsonRes);
		
		String xmlRes = r.request(MediaType.APPLICATION_XML).get(String.class);
		System.out.println(xmlRes);
		
		// 2, get response and headers etc, wrapped in ClientResponse
		Response response = r.request().get(Response.class);
		System.out.println( response.getStatus() );
		System.out.println( response.getHeaders().get("Content-Type") );
		String entity = response.readEntity(String.class);
		System.out.println(entity);
		
		// 3, get JAXB response
		GenericType<List<Title>> genericType = new GenericType<List<Title>>() {};
		List<Title> titles = r.request(MediaType.APPLICATION_XML).get(genericType);
		System.out.println("No. of Titles: " + titles.size());
		Title title = titles.get(0);
		System.out.println(title.getId() + ": " + title.getName());
	}
	
	public static void getOneContact(WebTarget r, String id) {
		GenericType<JAXBElement<Title>> generic = new GenericType<JAXBElement<Title>>() {};
		JAXBElement<Title> jaxbTitle = r.path(id).request(MediaType.APPLICATION_XML).get(generic);
		Title title = jaxbTitle.getValue();
		System.out.println(title.getId() + ": " + title.getName());
	}
	
	public static void postForm(WebTarget r, String name, String year, String synopsis) {
		Form form = new Form();
		form.param("name", name);
		form.param("year", year);
		form.param("synopsis", synopsis);
		Response response = r.request(MediaType.TEXT_HTML).post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE), Response.class);
		System.out.println(response.readEntity(String.class));
	}
	
	public static void putOneContact(WebTarget r, Title t) {
		Response response = r.path(t.getId().toString()).request(MediaType.APPLICATION_XML)
								   .put(Entity.entity(t, MediaType.APPLICATION_XML_TYPE) ,Response.class);
		System.out.println(response.getStatus());
	}
	
	public static void deleteOneContact(WebTarget r, String id) {
		Response response = r.path(id).request().delete(Response.class);
		System.out.println(response.getStatus());
	}
}
