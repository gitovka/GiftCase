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


package eu.fp7.scase.giftcaseapi.MusicMovieGift;


import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import javax.xml.bind.annotation.XmlRootElement;
import eu.fp7.scase.giftcaseapi.utilities.HypermediaLink;


/* This class models the data of a MusicMovieGift resource. It is enhanced with JAXB annotations for automated representation
parsing/marshalling as well as with Hibernate annotations for ORM transformations.*/
@XmlRootElement
@Entity
@Table(name="\"musicmoviegift\"")
public class JavaMusicMovieGiftModel{


    /* There follows a list with the properties that model the MusicMovieGift resource, as prescribed in the service CIM*/
		@Column(name = "\"condition\"")
		private String condition;

		@Column(name = "\"sender\"")
		private String sender;

		@Column(name = "\"status\"")
		private String status;

		@Column(name = "\"description\"")
		private String description;

		@Column(name = "\"regularPrice\"")
		private Double regularPrice;

		@Column(name = "\"image\"")
		private String image;

		@Column(name = "\"name\"")
		private String name;

		@Column(name = "\"recipient\"")
		private String recipient;

		@Column(name = "\"categoryid\"")
		private int categoryId;

		@Column(name = "\"sendtime\"")
		private String sendTime;

		@Id
		@GeneratedValue
		@Column(name = "\"musicmoviegiftid\"")
		private int MusicMovieGiftId;

		// The Linklist property holds all the hypermedia links to be sent back to the client
		@Transient
		private List<HypermediaLink> linklist = new ArrayList<HypermediaLink>();



    /* There follows a list of setter and getter functions.*/
	    public void setcondition(String condition){
        	this.condition = condition;
    	}

	    public void setsender(String sender){
        	this.sender = sender;
    	}

	    public void setstatus(String status){
        	this.status = status;
    	}

	    public void setdescription(String description){
        	this.description = description;
    	}

	    public void setregularPrice(Double regularPrice){
        	this.regularPrice = regularPrice;
    	}

	    public void setimage(String image){
        	this.image = image;
    	}

	    public void setname(String name){
        	this.name = name;
    	}

	    public void setrecipient(String recipient){
        	this.recipient = recipient;
    	}

	    public void setcategoryId(int categoryId){
        	this.categoryId = categoryId;
    	}

	    public void setsendTime(String sendTime){
        	this.sendTime = sendTime;
    	}

	    public void setMusicMovieGiftId(int MusicMovieGiftId){
        	this.MusicMovieGiftId = MusicMovieGiftId;
    	}

	    public void setlinklist(List<HypermediaLink> linklist){
        	this.linklist = linklist;
    	}



	    public String getcondition(){
        	return this.condition;
    	}

	    public String getsender(){
        	return this.sender;
    	}

	    public String getstatus(){
        	return this.status;
    	}

	    public String getdescription(){
        	return this.description;
    	}

	    public Double getregularPrice(){
        	return this.regularPrice;
    	}

	    public String getimage(){
        	return this.image;
    	}

	    public String getname(){
        	return this.name;
    	}

	    public String getrecipient(){
        	return this.recipient;
    	}

	    public int getcategoryId(){
        	return this.categoryId;
    	}

	    public String getsendTime(){
        	return this.sendTime;
    	}

	    public int getMusicMovieGiftId(){
        	return this.MusicMovieGiftId;
    	}

	    public List<HypermediaLink> getlinklist(){
        	return this.linklist;
    	}

}
