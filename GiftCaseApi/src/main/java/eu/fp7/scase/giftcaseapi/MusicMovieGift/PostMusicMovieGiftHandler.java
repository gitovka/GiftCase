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


/* This class processes POST requests for MusicMovieGift resources and creates the hypermedia to be returned to the client*/

public class PostMusicMovieGiftHandler{


    private HibernateController oHibernateController;
    private UriInfo oApplicationUri; //Standard datatype that holds information on the URI info of this request
	private String strResourcePath; //relative path to the current resource
    private JavaMusicMovieGiftModel oJavaMusicMovieGiftModel;

    public PostMusicMovieGiftHandler(JavaMusicMovieGiftModel oJavaMusicMovieGiftModel, UriInfo oApplicationUri){
        this.oJavaMusicMovieGiftModel = oJavaMusicMovieGiftModel;
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

    public JavaMusicMovieGiftModel postJavaMusicMovieGiftModel(){


		return createHypermedia(oHibernateController.postMusicMovieGift(oJavaMusicMovieGiftModel));
    }


    /* This function produces hypermedia links to be sent to the client so as it will be able to forward the application state in a valid way.*/
    public JavaMusicMovieGiftModel createHypermedia(JavaMusicMovieGiftModel oJavaMusicMovieGiftModel){

		        /* Create hypermedia links towards this specific MusicMovieGift resource. These must be GET and POST as it is prescribed in the meta-models.*/
		        oJavaMusicMovieGiftModel.getlinklist().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), this.strResourcePath), "Get all MusicMovieGifts", "GET", "Sibling"));
		        oJavaMusicMovieGiftModel.getlinklist().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), this.strResourcePath), "Create a new MusicMovieGift", "POST", "Sibling"));
		
		        /* Then calculate the relative path to any resources that are related of this one and add the according hypermedia links to the Linklist.*/
		        String oRelativePath;
		        oRelativePath = this.strResourcePath;
		        oJavaMusicMovieGiftModel.getlinklist().add(new HypermediaLink(String.format("%s%s/%d", oApplicationUri.getBaseUri(), oRelativePath, oJavaMusicMovieGiftModel.getMusicMovieGiftId()), String.valueOf(oJavaMusicMovieGiftModel.getsendTime()), "GET", "Child", oJavaMusicMovieGiftModel.getMusicMovieGiftId()));
		

        return oJavaMusicMovieGiftModel;
    }
}
