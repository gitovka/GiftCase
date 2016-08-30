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


package eu.fp7.scase.giftcaseapi.Users;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import javax.xml.bind.annotation.XmlRootElement;


import eu.fp7.scase.giftcaseapi.utilities.HypermediaLink;



/* This class models the data of a Users resource. It is enhanced with JAXB annotations for automated representation
parsing/marshalling as well as with Hibernate annotations for ORM transformations.*/
@XmlRootElement
@Entity
@Table(name="\"users\"")
public class JavaUsersModel{


    /* There follows a list with the properties that model the Users resource, as prescribed in the service CIM*/
		@Column(name = "\"lastname\"")
		private String lastName;

		@Column(name = "\"email\"")
		private String email;

		@Column(name = "\"firstname\"")
		private String firstName;

		@Column(name = "\"location\"")
		private String location;

		@Column(name = "\"hometown\"")
		private String hometown;

		@Column(name = "\"accesstoken\"")
		private String accesstoken;

		@Column(name = "\"facebookid\"")
		private String facebookId;

		@Column(name = "\"profilepicture\"")
		private String profilePicture;

		@Column(name = "\"isuserloggedin\"")
		private boolean isuserloggedin;

		@Column(name = "\"imsi\"")
		private String imsi;

		@Id
		@GeneratedValue
		@Column(name = "\"usersid\"")
		private int UsersId;

		// The Linklist property holds all the hypermedia links to be sent back to the client
		@Transient
		private List<HypermediaLink> linklist = new ArrayList<HypermediaLink>();



    /* There follows a list of setter and getter functions.*/
	    public void setlastName(String lastName){
        	this.lastName = lastName;
    	}

	    public void setemail(String email){
        	this.email = email;
    	}

	    public void setfirstName(String firstName){
        	this.firstName = firstName;
    	}

	    public void setlocation(String location){
        	this.location = location;
    	}

	    public void sethometown(String hometown){
        	this.hometown = hometown;
    	}

	    public void setaccesstoken(String accesstoken){
        	this.accesstoken = accesstoken;
    	}

	    public void setfacebookId(String facebookId){
        	this.facebookId = facebookId;
    	}

	    public void setprofilePicture(String profilePicture){
        	this.profilePicture = profilePicture;
    	}

	    public void setisuserloggedin(boolean isuserloggedin){
        	this.isuserloggedin = isuserloggedin;
    	}

	    public void setimsi(String imsi){
        	this.imsi = imsi;
    	}

	    public void setUsersId(int UsersId){
        	this.UsersId = UsersId;
    	}

	    public void setlinklist(List<HypermediaLink> linklist){
        	this.linklist = linklist;
    	}



	    public String getlastName(){
        	return this.lastName;
    	}

	    public String getemail(){
        	return this.email;
    	}

	    public String getfirstName(){
        	return this.firstName;
    	}

	    public String getlocation(){
        	return this.location;
    	}

	    public String gethometown(){
        	return this.hometown;
    	}

	    public String getaccesstoken(){
        	return this.accesstoken;
    	}

	    public String getfacebookId(){
        	return this.facebookId;
    	}

	    public String getprofilePicture(){
        	return this.profilePicture;
    	}

	    public boolean getisuserloggedin(){
        	return this.isuserloggedin;
    	}

	    public String getimsi(){
        	return this.imsi;
    	}

	    public int getUsersId(){
        	return this.UsersId;
    	}

	    public List<HypermediaLink> getlinklist(){
        	return this.linklist;
    	}



}
