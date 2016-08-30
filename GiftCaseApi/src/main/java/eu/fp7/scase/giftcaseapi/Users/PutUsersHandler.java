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


/* This class processes PUT requests for Users resources and creates the hypermedia to be returned to the client*/
public class PutUsersHandler{


    private HibernateController oHibernateController;
    private UriInfo oApplicationUri; //Standard datatype that holds information on the URI info of this request
	private String strResourcePath; //relative path to the current resource
    private JavaUsersModel oJavaUsersModel;

    public PutUsersHandler(int UsersId, JavaUsersModel oJavaUsersModel, UriInfo oApplicationUri){
        this.oJavaUsersModel = oJavaUsersModel;
        this.oJavaUsersModel.setUsersId(UsersId);
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

    public JavaUsersModel putJavaUsersModel(){


		return createHypermedia(oHibernateController.putUsers(oJavaUsersModel));
    }


    /* This function produces hypermedia links to be sent to the client so as it will be able to forward the application state in a valid way.*/
    public JavaUsersModel createHypermedia(JavaUsersModel oJavaUsersModel){

		        /* Create hypermedia links towards this specific Users resource. These can be GET, PUT and/or delete depending on what was specified in the service CIM.*/
		        oJavaUsersModel.getlinklist().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), this.strResourcePath), "Get the Users", "GET", "Sibling"));
		        oJavaUsersModel.getlinklist().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), this.strResourcePath), "Update the Users", "PUT", "Sibling"));
		
		
		        /* Finally, truncate the current URI so as to point to the resource manager of which this resource is related.
		        Then create the hypermedia links towards the parent resources.*/
		        int iLastSlashIndex = String.format("%s%s", oApplicationUri.getBaseUri(), this.strResourcePath).lastIndexOf("/");
			    oJavaUsersModel.getlinklist().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), this.strResourcePath).substring(0, iLastSlashIndex), "Create a new Users", "POST", "Parent"));
		        oJavaUsersModel.getlinklist().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), this.strResourcePath).substring(0, iLastSlashIndex), "Get all Userss", "GET", "Parent"));
		

        return oJavaUsersModel;
    }
}
