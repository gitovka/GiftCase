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


import javax.ws.rs.core.UriInfo;

import eu.fp7.scase.giftcaseapi.utilities.HypermediaLink;
import eu.fp7.scase.giftcaseapi.utilities.HibernateController;


/* This class processes POST requests for Users resources and creates the hypermedia to be returned to the client*/

public class PostUsersHandler{


    private HibernateController oHibernateController;
    private UriInfo oApplicationUri; //Standard datatype that holds information on the URI info of this request
	private String strResourcePath; //relative path to the current resource
    private JavaUsersModel oJavaUsersModel;

    public PostUsersHandler(JavaUsersModel oJavaUsersModel, UriInfo oApplicationUri){
        this.oJavaUsersModel = oJavaUsersModel;
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

    public JavaUsersModel postJavaUsersModel(){


		return createHypermedia(oHibernateController.postUsers(oJavaUsersModel));
    }


    /* This function produces hypermedia links to be sent to the client so as it will be able to forward the application state in a valid way.*/
    public JavaUsersModel createHypermedia(JavaUsersModel oJavaUsersModel){

		        /* Create hypermedia links towards this specific Users resource. These must be GET and POST as it is prescribed in the meta-models.*/
		        oJavaUsersModel.getlinklist().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), this.strResourcePath), "Get all Userss", "GET", "Sibling"));
		        oJavaUsersModel.getlinklist().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), this.strResourcePath), "Create a new Users", "POST", "Sibling"));
		
		        /* Then calculate the relative path to any resources that are related of this one and add the according hypermedia links to the Linklist.*/
		        String oRelativePath;
		        oRelativePath = this.strResourcePath;
		        oJavaUsersModel.getlinklist().add(new HypermediaLink(String.format("%s%s/%d", oApplicationUri.getBaseUri(), oRelativePath, oJavaUsersModel.getUsersId()), String.valueOf(oJavaUsersModel.getfacebookId()), "GET", "Child", oJavaUsersModel.getUsersId()));
		        oJavaUsersModel.getlinklist().add(new HypermediaLink(String.format("%s%s/%d", oApplicationUri.getBaseUri(), oRelativePath, oJavaUsersModel.getUsersId()), String.valueOf(oJavaUsersModel.getfacebookId()), "PUT", "Child", oJavaUsersModel.getUsersId()));
		

        return oJavaUsersModel;
    }
}
