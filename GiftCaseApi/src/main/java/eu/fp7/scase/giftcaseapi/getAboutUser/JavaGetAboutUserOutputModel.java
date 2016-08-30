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


package eu.fp7.scase.giftcaseapi.getAboutUser;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import eu.fp7.scase.giftcaseapi.utilities.HypermediaLink;

/* 
 * JavaGetAboutUserOutputModel class is responsible to model the output data model of the getAboutUser resource. This models the data
 * that will be received as output from the third party service.
*/

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class JavaGetAboutUserOutputModel{

    /* There follows a list with the properties that model the getAboutUser resource, as prescribed in the External service layer CIM*/
	private String id;
	private ComplexPicture picture;
	private String email;
	private String first_name;
	private String last_name;
	private ComplexLocation location;
	private String birthday;
	private ComplexHometown hometown;

	// The Linklist property holds all the hypermedia links to be sent back to the client
	@Transient
	private List<HypermediaLink> linklist = new ArrayList<HypermediaLink>();

	/* There follows a list of setter and getter functions.*/
	public void setId(String id){
    	this.id = id;
    }

	public void setPicture(ComplexPicture picture){
    	this.picture = picture;
    }

	public void setEmail(String email){
    	this.email = email;
    }

	public void setFirst_name(String first_name){
    	this.first_name = first_name;
    }

	public void setLast_name(String last_name){
    	this.last_name = last_name;
    }

	public void setLocation(ComplexLocation location){
    	this.location = location;
    }

	public void setBirthday(String birthday){
    	this.birthday = birthday;
    }

	public void setHometown(ComplexHometown hometown){
    	this.hometown = hometown;
    }

    public void setlinklist(List<HypermediaLink> linklist){
       	this.linklist = linklist;
   	}

	public String getId(){
        return this.id;
    }

	public ComplexPicture getPicture(){
        return this.picture;
    }

	public String getEmail(){
        return this.email;
    }

	public String getFirst_name(){
        return this.first_name;
    }

	public String getLast_name(){
        return this.last_name;
    }

	public ComplexLocation getLocation(){
        return this.location;
    }

	public String getBirthday(){
        return this.birthday;
    }

	public ComplexHometown getHometown(){
        return this.hometown;
    }
	public List<HypermediaLink> getlinklist(){
        return this.linklist;
    }
}
