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

package eu.fp7.scase.giftcaseapi.getFacebookFriends;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/* 
 * ComplexData class is responsible to model the corresponding Complex type that is used while this service interoperates with a 3rd party one.
*/

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComplexData{

    /* There follows a list with the properties that model the ComplexData Complex Type, as prescribed in the External service layer CIM*/
	@JsonProperty("first_name")
	private String first_name;
	@JsonProperty("id")
	private String id;
	@JsonProperty("birthday")
	private String birthday;
	@JsonProperty("last_name")
	private String last_name;
	@JsonProperty("location")
	private ComplexLocation location;

	/* There follows a list of setter and getter functions.*/
	public void setFirst_name(String first_name){
    	this.first_name = first_name;
    }

	public void setId(String id){
    	this.id = id;
    }

	public void setBirthday(String birthday){
    	this.birthday = birthday;
    }

	public void setLast_name(String last_name){
    	this.last_name = last_name;
    }

	public void setLocation(ComplexLocation location){
    	this.location = location;
    }

	public String getFirst_name(){
        return this.first_name;
    }

	public String getId(){
        return this.id;
    }

	public String getBirthday(){
        return this.birthday;
    }

	public String getLast_name(){
        return this.last_name;
    }

	public ComplexLocation getLocation(){
        return this.location;
    }
}
