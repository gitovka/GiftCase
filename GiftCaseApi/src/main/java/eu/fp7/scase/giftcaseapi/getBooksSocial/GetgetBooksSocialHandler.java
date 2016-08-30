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

package eu.fp7.scase.giftcaseapi.getBooksSocial;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import javax.ws.rs.core.UriInfo;
import javax.ws.rs.WebApplicationException;

import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import eu.fp7.scase.giftcaseapi.utilities.HypermediaLink;
import eu.fp7.scase.giftcaseapi.utilities.HibernateController;



/* 
 *This class processes client requests for getBooksSocial resource that are to be delegated to an existing 3rd party service. 
 *Uppon its output receival, this class repackages the output and creates the hypermedia links with the search results to be returned to the client
*/
public class GetgetBooksSocialHandler{

    private HibernateController oHibernateController;
    private UriInfo oApplicationUri; //Standard datatype that holds information on the URI info of this request
	private JavaGetBooksSocialOutputModel oOutputDataModel;
	private JSONObject o;
	private String country0;
	private String maxResults;
	private String printType;
	private String fields;
	private String k;
	private String access_token;
	private String limit;
	private String type;
	private String q_num;
	private String q0_num;
	private ClientConfig oClientConfiguration;

    public GetgetBooksSocialHandler(String country0, String maxResults, String printType, String fields, String k, String access_token, String limit, String type, String q_num, String q0_num, UriInfo oApplicationUri){
        this.oHibernateController = HibernateController.getHibernateControllerHandle();
        this.oApplicationUri = oApplicationUri;
		this.country0 = country0;
		this.maxResults = maxResults;
		this.printType = printType;
		this.fields = fields;
		this.k = k;
		this.access_token = access_token;
		this.limit = limit;
		this.type = type;
		this.q_num = q_num;
		this.q0_num = q0_num;

		//initialize JAX-RS Client configuration 
		this.oClientConfiguration = new DefaultClientConfig();
		this.oClientConfiguration.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
    }

    public JavaGetBooksSocialOutputModel getgetbookssocial(){

		//Return any results in the hypermedia links form.
        return interoperateWithExternalService();
    }

    /* 
	 * This function handles the interoperation with the existing 3rd party service. It calls the according functions to create the input 
	 * to be sent to it, to receive its output and if necessary to persist the outcome in the database. 
	 * Finally, the result is returned to the client.
    */
    public JavaGetBooksSocialOutputModel interoperateWithExternalService(){
		
		//create a new JAX-RS client
        Client oJAXRSRESTClient = Client.create(this.oClientConfiguration);

		//construct the GET query
		WebResource oTargetResource = oJAXRSRESTClient.resource("http://109.231.127.61:8080/GetBooksSocial-0.0.1-SNAPSHOT/rest/result/query").queryParam("country0", this.country0).queryParam("maxResults", this.maxResults).queryParam("printType", this.printType).queryParam("fields", this.fields).queryParam("k", this.k).queryParam("access_token", this.access_token).queryParam("limit", this.limit).queryParam("type", this.type).queryParam("q_num", this.q_num).queryParam("q0_num" , this.q0_num);
		
		System.out.println(oTargetResource);
		
        ClientResponse oResponse = oTargetResource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(ClientResponse.class);
        System.out.println(oResponse);
        
        this.oOutputDataModel =  oResponse.getEntity(JavaGetBooksSocialOutputModel.class);
        System.out.println(this.oOutputDataModel);
        if (isSuccessfullResponseCode(oResponse) == false) {
            throw new WebApplicationException();
        }	

		return this.oOutputDataModel;
	}
    

	/*
	 * This function checks the response code of the server reply against the known success codes. If the server response HTTP
     * code is within the success code range, this functions returns true. Otherwise it returns false
	*/
	private boolean isSuccessfullResponseCode(ClientResponse oResponse){
		if(oResponse.getStatus() == 200){ // Status OK
			return true;
		}
		else if(oResponse.getStatus() == 201){ // Status CREATED
			return true;
		}
		else if(oResponse.getStatus() == 202){ // Status Accepted
			return true;
		}
		else if(oResponse.getStatus() == 203){ // Status NON-AUTHORITATIVE INFORMATION
			return true;
		}
		else if(oResponse.getStatus() == 204){ // Status NO CONTENT
			return true;
		}
		else if(oResponse.getStatus() == 205){ // Status RESET CONTENT
			return true;
		}
		else if(oResponse.getStatus() == 206){ // Status PARTIAL CONTENT
			return true;
		}

		return false;
	}
	
}
