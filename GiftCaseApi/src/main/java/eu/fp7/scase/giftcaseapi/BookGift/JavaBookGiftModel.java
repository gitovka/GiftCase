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

package eu.fp7.scase.giftcaseapi.BookGift;

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

/* This class models the data of a BookGift resource. It is enhanced with JAXB annotations for automated representation
parsing/marshalling as well as with Hibernate annotations for ORM transformations.*/
@XmlRootElement
@Entity
@Table(name="\"bookgift\"")
public class JavaBookGiftModel{


    /* There follows a list with the properties that model the BookGift resource, as prescribed in the service CIM*/
		@Column(name = "\"category\"")
		private String category;

		@Column(name = "\"recipient\"")
		private String recipient;

		@Column(name = "\"publisher\"")
		private String publisher;

		@Column(name = "\"currencycode\"")
		private String currencyCode;

		@Column(name = "\"authors\"")
		private String authors;

		@Column(name = "\"title\"")
		private String title;

		@Column(name = "\"thumbnail\"")
		private String thumbnail;

		@Column(name = "\"status\"")
		private String status;

		@Column(name = "\"language\"")
		private String language;

		@Column(name = "\"sender\"")
		private String sender;

		@Column(name = "\"amount\"")
		private double amount;

		@Column(name = "\"pagecount\"")
		private int pageCount;

		@Column(name = "\"categoryid\"")
		private int categoryId;

		@Column(name = "\"sendtime\"")
		private long sendTime;

		@Id
		@GeneratedValue
		@Column(name = "\"bookgiftid\"")
		private int BookGiftId;

		// The Linklist property holds all the hypermedia links to be sent back to the client
		@Transient
		private List<HypermediaLink> linklist = new ArrayList<HypermediaLink>();



    /* There follows a list of setter and getter functions.*/
	    public void setcategory(String category){
        	this.category = category;
    	}

	    public void setrecipient(String recipient){
        	this.recipient = recipient;
    	}

	    public void setpublisher(String publisher){
        	this.publisher = publisher;
    	}

	    public void setcurrencyCode(String currencyCode){
        	this.currencyCode = currencyCode;
    	}

	    public void setauthors(String authors){
        	this.authors = authors;
    	}

	    public void settitle(String title){
        	this.title = title;
    	}

	    public void setthumbnail(String thumbnail){
        	this.thumbnail = thumbnail;
    	}

	    public void setstatus(String status){
        	this.status = status;
    	}

	    public void setlanguage(String language){
        	this.language = language;
    	}

	    public void setsender(String sender){
        	this.sender = sender;
    	}

	    public void setamount(double amount){
        	this.amount = amount;
    	}

	    public void setpageCount(int pageCount){
        	this.pageCount = pageCount;
    	}

	    public void setcategoryId(int categoryId){
        	this.categoryId = categoryId;
    	}

	    public void setsendTime(long sendTime){
        	this.sendTime = sendTime;
    	}

	    public void setBookGiftId(int BookGiftId){
        	this.BookGiftId = BookGiftId;
    	}

	    public void setlinklist(List<HypermediaLink> linklist){
        	this.linklist = linklist;
    	}

	    public String getcategory(){
        	return this.category;
    	}

	    public String getrecipient(){
        	return this.recipient;
    	}

	    public String getpublisher(){
        	return this.publisher;
    	}

	    public String getcurrencyCode(){
        	return this.currencyCode;
    	}

	    public String getauthors(){
        	return this.authors;
    	}

	    public String gettitle(){
        	return this.title;
    	}

	    public String getthumbnail(){
        	return this.thumbnail;
    	}

	    public String getstatus(){
        	return this.status;
    	}

	    public String getlanguage(){
        	return this.language;
    	}

	    public String getsender(){
        	return this.sender;
    	}

	    public double getamount(){
        	return this.amount;
    	}

	    public int getpageCount(){
        	return this.pageCount;
    	}

	    public int getcategoryId(){
        	return this.categoryId;
    	}

	    public long getsendTime(){
        	return this.sendTime;
    	}

	    public int getBookGiftId(){
        	return this.BookGiftId;
    	}

	    public List<HypermediaLink> getlinklist(){
        	return this.linklist;
    	}



}
