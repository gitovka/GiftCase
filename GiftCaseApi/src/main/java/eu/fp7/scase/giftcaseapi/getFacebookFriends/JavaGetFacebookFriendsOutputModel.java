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

import eu.fp7.scase.giftcaseapi.utilities.HypermediaLink;

/* 
 * JavaGetFacebookFriendsOutputModel class is responsible to model the output data model of the getFacebookFriends resource. This models the data
 * that will be received as output from the third party service.
*/

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class JavaGetFacebookFriendsOutputModel{

    /* There follows a list with the properties that model the getFacebookFriends resource, as prescribed in the External service layer CIM*/
	private ComplexFriends friends;

	// The Linklist property holds all the hypermedia links to be sent back to the client
	@Transient
	private List<HypermediaLink> linklist = new ArrayList<HypermediaLink>();

	/* There follows a list of setter and getter functions.*/
	public void setFriends(ComplexFriends friends){
    	this.friends = friends;
    }

    public void setlinklist(List<HypermediaLink> linklist){
       	this.linklist = linklist;
   	}

	public ComplexFriends getFriends(){
        return this.friends;
    }
	public List<HypermediaLink> getlinklist(){
        return this.linklist;
    }
}
