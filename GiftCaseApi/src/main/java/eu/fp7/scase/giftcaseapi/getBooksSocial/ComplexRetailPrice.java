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
 * ComplexRetailPrice class is responsible to model the corresponding Complex type that is used while this service interoperates with a 3rd party one.
*/

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComplexRetailPrice{

    /* There follows a list with the properties that model the ComplexRetailPrice Complex Type, as prescribed in the External service layer CIM*/
	@JsonProperty("amount")
	private Double amount;
	@JsonProperty("currencyCode")
	private String currencyCode;

	/* There follows a list of setter and getter functions.*/
	public void setAmount(Double amount){
    	this.amount = amount;
    }

	public void setCurrencyCode(String currencyCode){
    	this.currencyCode = currencyCode;
    }

	public Double getAmount(){
        return this.amount;
    }

	public String getCurrencyCode(){
        return this.currencyCode;
    }
}
