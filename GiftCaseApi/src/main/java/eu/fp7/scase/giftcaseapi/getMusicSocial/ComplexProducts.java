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

package eu.fp7.scase.giftcaseapi.getMusicSocial;

import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/* 
 * ComplexProducts class is responsible to model the corresponding Complex type that is used while this service interoperates with a 3rd party one.
*/

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComplexProducts{

    /* There follows a list with the properties that model the ComplexProducts Complex Type, as prescribed in the External service layer CIM*/
	@JsonProperty("name")
	private String name;
	@JsonProperty("regularPrice")
	private Double regularPrice;
	@JsonProperty("shortDescription")
	private String shortDescription;
	@JsonProperty("image")
	private String image;

	/* There follows a list of setter and getter functions.*/
	public void setName(String name){
    	this.name = name;
    }

	public void setRegularPrice(Double regularPrice){
    	this.regularPrice = regularPrice;
    }

	public void setShortDescription(String shortDescription){
    	this.shortDescription = shortDescription;
    }

	public void setImage(String image){
    	this.image = image;
    }

	public String getName(){
        return this.name;
    }

	public Double getRegularPrice(){
        return this.regularPrice;
    }

	public String getShortDescription(){
        return this.shortDescription;
    }

	public String getImage(){
        return this.image;
    }
}
