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

package eu.fp7.scase.giftcaseapi.getEventsSocial;

import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/* 
 * ComplexEvent class is responsible to model the corresponding Complex type that is used while this service interoperates with a 3rd party one.
*/

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComplexEvent{

    /* There follows a list with the properties that model the ComplexEvent Complex Type, as prescribed in the External service layer CIM*/
	@JsonProperty("city_name")
	private String city_name;
	@JsonProperty("country_name")
	private String country_name;
	@JsonProperty("description")
	private String description;
	@JsonProperty("price")
	private String price;
	@JsonProperty("start_time")
	private String start_time;
	@JsonProperty("title")
	private String title;
	@JsonProperty("image")
	private ComplexImage image;

	/* There follows a list of setter and getter functions.*/
	public void setCity_name(String city_name){
    	this.city_name = city_name;
    }

	public void setCountry_name(String country_name){
    	this.country_name = country_name;
    }

	public void setDescription(String description){
    	this.description = description;
    }

	public void setPrice(String price){
    	this.price = price;
    }

	public void setStart_time(String start_time){
    	this.start_time = start_time;
    }

	public void setTitle(String title){
    	this.title = title;
    }

	public void setImage(ComplexImage image){
    	this.image = image;
    }

	public String getCity_name(){
        return this.city_name;
    }

	public String getCountry_name(){
        return this.country_name;
    }

	public String getDescription(){
        return this.description;
    }

	public String getPrice(){
        return this.price;
    }

	public String getStart_time(){
        return this.start_time;
    }

	public String getTitle(){
        return this.title;
    }

	public ComplexImage getImage(){
        return this.image;
    }
}
