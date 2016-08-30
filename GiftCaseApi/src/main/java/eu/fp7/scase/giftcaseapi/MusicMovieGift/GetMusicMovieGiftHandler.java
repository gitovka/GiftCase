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


import javax.ws.rs.core.UriInfo;

import eu.fp7.scase.giftcaseapi.utilities.HypermediaLink;
import eu.fp7.scase.giftcaseapi.utilities.HibernateController;


/* This class processes GET requests for MusicMovieGift resources and creates the hypermedia to be returned to the client*/
public class GetMusicMovieGiftHandler{


    private HibernateController oHibernateController;
    private UriInfo oApplicationUri; //Standard datatype that holds information on the URI info of this request
	private String strResourcePath; //relative path to the current resource
    private JavaMusicMovieGiftModel oJavaMusicMovieGiftModel;

    public GetMusicMovieGiftHandler(int MusicMovieGiftId, UriInfo oApplicationUri){
        oJavaMusicMovieGiftModel = new JavaMusicMovieGiftModel();
        oJavaMusicMovieGiftModel.setMusicMovieGiftId(MusicMovieGiftId);
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

    public JavaMusicMovieGiftModel getJavaMusicMovieGiftModel(){


		return createHypermedia(oHibernateController.getMusicMovieGift(oJavaMusicMovieGiftModel));
	}


    /* This function produces hypermedia links to be sent to the client so as it will be able to forward the application state in a valid way.*/
    public JavaMusicMovieGiftModel createHypermedia(JavaMusicMovieGiftModel oJavaMusicMovieGiftModel){

		        /* Create hypermedia links towards this specific MusicMovieGift resource. These can be GET, PUT and/or delete depending on what was specified in the service CIM.*/
		        oJavaMusicMovieGiftModel.getlinklist().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), this.strResourcePath), "Get the MusicMovieGift", "GET", "Sibling"));
		
		
		        /* Finally, truncate the current URI so as to point to the resource manager of which this resource is related.
		        Then create the hypermedia links towards the parent resources.*/
		        int iLastSlashIndex = String.format("%s%s", oApplicationUri.getBaseUri(), this.strResourcePath).lastIndexOf("/");
		        oJavaMusicMovieGiftModel.getlinklist().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), this.strResourcePath).substring(0, iLastSlashIndex), "Create a new MusicMovieGift", "POST", "Parent"));
		        oJavaMusicMovieGiftModel.getlinklist().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), this.strResourcePath).substring(0, iLastSlashIndex), "Get all MusicMovieGifts", "GET", "Parent"));

        return oJavaMusicMovieGiftModel;
    }
}
