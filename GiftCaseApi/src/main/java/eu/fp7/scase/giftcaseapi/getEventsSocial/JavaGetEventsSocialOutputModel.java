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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import eu.fp7.scase.giftcaseapi.utilities.HypermediaLink;

/* 
 * JavaGetEventsSocialOutputModel class is responsible to model the output data model of the getEventsSocial resource. This models the data
 * that will be received as output from the third party service.
*/

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class JavaGetEventsSocialOutputModel{

    /* There follows a list with the properties that model the getEventsSocial resource, as prescribed in the External service layer CIM*/
	private List<ComplexSearchResponse> get_search_response = new ArrayList<ComplexSearchResponse>();

	// The Linklist property holds all the hypermedia links to be sent back to the client
	@Transient
	private List<HypermediaLink> linklist = new ArrayList<HypermediaLink>();

	/* There follows a list of setter and getter functions.*/
	public void setGet_search_response(List<ComplexSearchResponse> get_search_response){
    	this.get_search_response = get_search_response;
    }

    public void setlinklist(List<HypermediaLink> linklist){
       	this.linklist = linklist;
   	}

	public List<ComplexSearchResponse> getGet_search_response(){
        return this.get_search_response;
    }
	public List<HypermediaLink> getlinklist(){
        return this.linklist;
    }
	

}
