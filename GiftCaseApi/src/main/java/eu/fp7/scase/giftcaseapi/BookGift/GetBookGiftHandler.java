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


import javax.ws.rs.core.UriInfo;

import eu.fp7.scase.giftcaseapi.utilities.HypermediaLink;
import eu.fp7.scase.giftcaseapi.utilities.HibernateController;


/* This class processes GET requests for BookGift resources and creates the hypermedia to be returned to the client*/
public class GetBookGiftHandler{


    private HibernateController oHibernateController;
    private UriInfo oApplicationUri; //Standard datatype that holds information on the URI info of this request
	private String strResourcePath; //relative path to the current resource
    private JavaBookGiftModel oJavaBookGiftModel;

    public GetBookGiftHandler(int BookGiftId, UriInfo oApplicationUri){
        oJavaBookGiftModel = new JavaBookGiftModel();
        oJavaBookGiftModel.setBookGiftId(BookGiftId);
        this.oHibernateController = HibernateController.getHibernateControllerHandle();
        this.oApplicationUri = oApplicationUri;
		this.strResourcePath = calculateProperResourcePath();
    }

	public String calculateProperResourcePath(){
    	if(this.oApplicationUri.getPath().lastIndexOf('/') == this.oApplicationUri.getPath().length() - 1){
        	return this.oApplicationUri.getPath().substring(0, this.oApplicationUri.getPath().length() - 1);
    	}
    	else{
        	return this.oApplicationUri.getPath();
    	}
	}

    public JavaBookGiftModel getJavaBookGiftModel(){


		return createHypermedia(oHibernateController.getBookGift(oJavaBookGiftModel));
	}


    /* This function produces hypermedia links to be sent to the client so as it will be able to forward the application state in a valid way.*/
    public JavaBookGiftModel createHypermedia(JavaBookGiftModel oJavaBookGiftModel){

		        /* Create hypermedia links towards this specific BookGift resource. These can be GET, PUT and/or delete depending on what was specified in the service CIM.*/
		        oJavaBookGiftModel.getlinklist().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), this.strResourcePath), "Get the BookGift", "GET", "Sibling"));
		
		
		        /* Finally, truncate the current URI so as to point to the resource manager of which this resource is related.
		        Then create the hypermedia links towards the parent resources.*/
		        int iLastSlashIndex = String.format("%s%s", oApplicationUri.getBaseUri(), this.strResourcePath).lastIndexOf("/");
		        oJavaBookGiftModel.getlinklist().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), this.strResourcePath).substring(0, iLastSlashIndex), "Create a new BookGift", "POST", "Parent"));
		        oJavaBookGiftModel.getlinklist().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), this.strResourcePath).substring(0, iLastSlashIndex), "Get all BookGifts", "GET", "Parent"));

        return oJavaBookGiftModel;
    }
}
