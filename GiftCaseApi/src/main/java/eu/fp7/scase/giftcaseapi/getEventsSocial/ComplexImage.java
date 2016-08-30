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
 * ComplexImage class is responsible to model the corresponding Complex type that is used while this service interoperates with a 3rd party one.
*/

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComplexImage{

    /* There follows a list with the properties that model the ComplexImage Complex Type, as prescribed in the External service layer CIM*/
	@JsonProperty("medium")
	private ComplexMedium medium;

	/* There follows a list of setter and getter functions.*/
	public void setMedium(ComplexMedium medium){
    	this.medium = medium;
    }

	public ComplexMedium getMedium(){
        return this.medium;
    }
}
