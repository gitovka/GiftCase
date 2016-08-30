/*
 * ARISTOTLE UNIVERSITY OF THESSALONIKI
 * Copyright (C) 2015
 * Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering
 * Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 *
 * Project             : GiftCaseApi
 * WorkFile            : 
 * Compiler            : 
 * File Description    : 
 * Document Description: 
* Related Documents	   : 
* Note				   : 
* Programmer		   : RESTful MDE Engine created by Christoforos Zolotas
* Contact			   : christopherzolotas@issel.ee.auth.gr
*/

package eu.fp7.scase.giftcaseapi.getBooksSocial;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/* 
 * ComplexVolumeInfo class is responsible to model the corresponding Complex type that is used while this service interoperates with a 3rd party one.
*/

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComplexVolumeInfo{

    /* There follows a list with the properties that model the ComplexVolumeInfo Complex Type, as prescribed in the External service layer CIM*/
	@JsonProperty("title")
	private String title;
	@JsonProperty("authors")
	private List<String> authors = new ArrayList<String>();
	@JsonProperty("publisher")
	private String publisher;
	@JsonProperty("description")
	private String description;
	@JsonProperty("pageCount")
	private Integer pageCount;
	@JsonProperty("categories")
	private List<String> categories = new ArrayList<String>();
	@JsonProperty("imageLinks")
	private ComplexImageLinks imageLinks;
	@JsonProperty("language")
	private String language;

	/* There follows a list of setter and getter functions.*/
	public void setTitle(String title){
    	this.title = title;
    }

	public void setAuthors(List<String> authors){
    	this.authors = authors;
    }

	public void setPublisher(String publisher){
    	this.publisher = publisher;
    }

	public void setDescription(String description){
    	this.description = description;
    }

	public void setPageCount(Integer pageCount){
    	this.pageCount = pageCount;
    }

	public void setCategories(List<String> categories){
    	this.categories = categories;
    }

	public void setImageLinks(ComplexImageLinks imageLinks){
    	this.imageLinks = imageLinks;
    }

	public void setLanguage(String language){
    	this.language = language;
    }

	public String getTitle(){
        return this.title;
    }

	public List<String> getAuthors(){
        return this.authors;
    }

	public String getPublisher(){
        return this.publisher;
    }

	public String getDescription(){
        return this.description;
    }

	public Integer getPageCount(){
        return this.pageCount;
    }

	public List<String> getCategories(){
        return this.categories;
    }

	public ComplexImageLinks getImageLinks(){
        return this.imageLinks;
    }

	public String getLanguage(){
        return this.language;
    }
}
