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

package eu.fp7.scase.giftcaseapi.getMoviesSocial;


import javax.ws.rs.core.UriInfo;
import javax.ws.rs.WebApplicationException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import eu.fp7.scase.giftcaseapi.utilities.HibernateController;



/* 
 *This class processes client requests for getMoviesSocial resource that are to be delegated to an existing 3rd party service. 
 *Uppon its output receival, this class repackages the output and creates the hypermedia links with the search results to be returned to the client
*/
public class GetgetMoviesSocialHandler{

    private HibernateController oHibernateController;
    private UriInfo oApplicationUri; //Standard datatype that holds information on the URI info of this request
	private JavaGetMoviesSocialOutputModel oOutputDataModel;
	private String access_token;
	private String fields;
	private String Type0;
	private String k;
	private String pageSize;
	private String apiKey;
	private String show;
	private String limit;
	private String format;
	private String sort;
	private String q_num;
	private String search_num;
	private ClientConfig oClientConfiguration;

    public GetgetMoviesSocialHandler(String access_token, String fields, String Type0, String k, String pageSize, String apiKey, String show, String limit, String format, String sort, String q_num, String search_num, UriInfo oApplicationUri){
        this.oHibernateController = HibernateController.getHibernateControllerHandle();
        this.oApplicationUri = oApplicationUri;
		this.access_token = access_token;
		this.fields = fields;
		this.Type0 = Type0;
		this.k = k;
		this.pageSize = pageSize;
		this.apiKey = apiKey;
		this.show = show;
		this.limit = limit;
		this.format = format;
		this.sort = sort;
		this.q_num= q_num;
		this.search_num = search_num;

		//initialize JAX-RS Client configuration 
		this.oClientConfiguration = new DefaultClientConfig();
		this.oClientConfiguration.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
    }

    public JavaGetMoviesSocialOutputModel getgetmoviessocial(){


		//Return any results in the hypermedia links form.
        return interoperateWithExternalService();
    }


    /* 
	 * This function handles the interoperation with the existing 3rd party service. It calls the according functions to create the input 
	 * to be sent to it, to receive its output and if necessary to persist the outcome in the database. 
	 * Finally, the result is returned to the client.
    */
    public JavaGetMoviesSocialOutputModel interoperateWithExternalService(){
		
		//create a new JAX-RS client
        Client oJAXRSRESTClient = Client.create(this.oClientConfiguration);

		//construct the GET query
		WebResource oTargetResource = oJAXRSRESTClient.resource("http://109.231.127.61:8080/GetMoviesSocial-0.0.1-SNAPSHOT/rest/result/query").queryParam("access_token", this.access_token).queryParam("fields", this.fields).queryParam("Type0", this.Type0).queryParam("k", this.k).queryParam("pageSize", this.pageSize).queryParam("apiKey", this.apiKey).queryParam("show", this.show).queryParam("limit", this.limit).queryParam("format", this.format).queryParam("sort", this.sort).queryParam("q_num", this.q_num).queryParam("search_num", this.search_num);
        ClientResponse oResponse = oTargetResource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(ClientResponse.class);
        this.oOutputDataModel = oResponse.getEntity(JavaGetMoviesSocialOutputModel.class);

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
