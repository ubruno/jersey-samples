package com.ericsson.charter.poc.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Title {

	private Integer id;
	private String name;
	private Integer year;
	private String synopsis;
	
	private static int titleCount = 1;
	
	public Title() {
		this.id = titleCount++;
	}
	
	/**
	 * Creates a new title object
	 * @param name the title name
	 * @param year the title year
	 * @param synopsis the title synopsis
	 */
	public Title(String name, Integer year, String synopsis) {
		this.id = titleCount++;
		this.name = name;
		this.year = year;
		this.synopsis = synopsis;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public Integer getYear() {
		return year;
	}
	
	public void setYear(Integer year) {
		this.year = year;
	}
	
}
