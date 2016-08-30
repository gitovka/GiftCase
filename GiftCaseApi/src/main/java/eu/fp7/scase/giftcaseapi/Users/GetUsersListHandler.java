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

import java.util.Iterator;
import eu.fp7.scase.giftcaseapi.utilities.HypermediaLink;
import eu.fp7.scase.giftcaseapi.utilities.HibernateController;
import java.util.Set;
import java.util.HashSet;

/* This class processes GET requests for Users resources and creates the hypermedia to be returned to the client*/

public class GetUsersListHandler{

    private HibernateController oHibernateController;
    private UriInfo oApplicationUri; //Standard datatype that holds information on the URI info of this request
	private String strResourcePath; //relative path to the current resource
    private Set<JavaUsersModel> SetOfUsersModel = new HashSet<JavaUsersModel>(); // Since this resource is not related of any other, this HashSet will hold the list to be sent back to client.

    public GetUsersListHandler(UriInfo oApplicationUri){
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

    public JavaUsersModelManager getJavaUsersModelManager(){


        this.SetOfUsersModel = oHibernateController.getUsersList(this.SetOfUsersModel);
        return createHypermedia();
    }


    /* This function produces hypermedia links to be sent to the client so as it will be able to forward the application state in a valid way.*/
    public JavaUsersModelManager createHypermedia(){
        JavaUsersModelManager oJavaUsersModelManager = new JavaUsersModelManager();

		        /* Create hypermedia links towards this specific Users resource. These must be GET and POST as it is prescribed in the meta-models.*/
		        oJavaUsersModelManager.getlinklist().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), this.strResourcePath), "Get all Userss", "GET", "Sibling"));
		        oJavaUsersModelManager.getlinklist().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), this.strResourcePath), "Create a new Users", "POST", "Sibling"));
		
		        /* Then calculate the relative path to any related resource of this one and add for each one a hypermedia link to the Linklist.*/
		        String oRelativePath;
		        oRelativePath = this.strResourcePath;
		        Iterator<JavaUsersModel> setIterator = this.SetOfUsersModel.iterator();
		        while(setIterator.hasNext()){
		            JavaUsersModel oNextJavaUsersModel = new JavaUsersModel();
		            oNextJavaUsersModel = setIterator.next();
		            oJavaUsersModelManager.getlinklist().add(new HypermediaLink(String.format("%s%s/%d", oApplicationUri.getBaseUri(), oRelativePath, oNextJavaUsersModel.getUsersId()), String.valueOf(oNextJavaUsersModel.getfacebookId()), "GET", "Child", oNextJavaUsersModel.getUsersId()));
		        }
		


        return oJavaUsersModelManager;
    }
}
