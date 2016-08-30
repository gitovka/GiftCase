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


package eu.fp7.scase.giftcaseapi.EventGift;


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



/* This class models the data of a EventGift resource. It is enhanced with JAXB annotations for automated representation
parsing/marshalling as well as with Hibernate annotations for ORM transformations.*/
@XmlRootElement
@Entity
@Table(name="\"eventgift\"")
public class JavaEventGiftModel{


    /* There follows a list with the properties that model the EventGift resource, as prescribed in the service CIM*/
		@Column(name = "\"status\"")
		private String status;

		@Column(name = "\"country_name\"")
		private String country_name;

		@Column(name = "\"city_name\"")
		private String city_name;

		@Column(name = "\"image\"")
		private String image;

		@Column(name = "\"description\"")
		private String description;

		@Column(name = "\"sender\"")
		private String sender;

		@Column(name = "\"start_time\"")
		private String start_time;

		@Column(name = "\"title\"")
		private String title;

		@Column(name = "\"price\"")
		private String price;

		@Column(name = "\"recipient\"")
		private String recipient;

		@Column(name = "\"categoryid\"")
		private int categoryId;

		@Column(name = "\"sendtime\"")
		private String sendTime;

		@Id
		@GeneratedValue
		@Column(name = "\"eventgiftid\"")
		private int EventGiftId;

		// The Linklist property holds all the hypermedia links to be sent back to the client
		@Transient
		private List<HypermediaLink> linklist = new ArrayList<HypermediaLink>();



    /* There follows a list of setter and getter functions.*/
	    public void setstatus(String status){
        	this.status = status;
    	}

	    public void setcountry_name(String country_name){
        	this.country_name = country_name;
    	}

	    public void setcity_name(String city_name){
        	this.city_name = city_name;
    	}

	    public void setimage(String image){
        	this.image = image;
    	}

	    public void setdescription(String description){
        	this.description = description;
    	}

	    public void setsender(String sender){
        	this.sender = sender;
    	}

	    public void setstart_time(String start_time){
        	this.start_time = start_time;
    	}

	    public void settitle(String title){
        	this.title = title;
    	}

	    public void setprice(String price){
        	this.price = price;
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

	    public void setEventGiftId(int EventGiftId){
        	this.EventGiftId = EventGiftId;
    	}

	    public void setlinklist(List<HypermediaLink> linklist){
        	this.linklist = linklist;
    	}



	    public String getstatus(){
        	return this.status;
    	}

	    public String getcountry_name(){
        	return this.country_name;
    	}

	    public String getcity_name(){
        	return this.city_name;
    	}

	    public String getimage(){
        	return this.image;
    	}

	    public String getdescription(){
        	return this.description;
    	}

	    public String getsender(){
        	return this.sender;
    	}

	    public String getstart_time(){
        	return this.start_time;
    	}

	    public String gettitle(){
        	return this.title;
    	}

	    public String getprice(){
        	return this.price;
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

	    public int getEventGiftId(){
        	return this.EventGiftId;
    	}

	    public List<HypermediaLink> getlinklist(){
        	return this.linklist;
    	}



}
