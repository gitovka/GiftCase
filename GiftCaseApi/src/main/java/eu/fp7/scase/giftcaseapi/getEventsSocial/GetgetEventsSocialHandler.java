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

package eu.fp7.scase.giftcaseapi.getEventsSocial;

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
 *This class processes client requests for getEventsSocial resource that are to be delegated to an existing 3rd party service. 
 *Uppon its output receival, this class repackages the output and creates the hypermedia links with the search results to be returned to the client
 */
public class GetgetEventsSocialHandler {

	private HibernateController oHibernateController;
	private UriInfo oApplicationUri; // Standard datatype that holds information
										// on the URI info of this request
	private JavaGetEventsSocialOutputModel oOutputDataModel;
	private String access_token;
	private String fields;
	private String units;
	private String within;
	private String location;
	private String include;
	private String app_key;
	private String keywords_num;
	private ClientConfig oClientConfiguration;

	public GetgetEventsSocialHandler(String access_token, String fields,
			String units, String within, String location, String include,
			String app_key, String keywords_num, UriInfo oApplicationUri) {
		this.oHibernateController = HibernateController
				.getHibernateControllerHandle();
		this.oApplicationUri = oApplicationUri;
		this.access_token = access_token;
		this.fields = fields;
		this.units = units;
		this.within = within;
		this.location = location;
		this.include = include;
		this.app_key = app_key;
		this.keywords_num = keywords_num;

		// initialize JAX-RS Client configuration
		this.oClientConfiguration = new DefaultClientConfig();
		this.oClientConfiguration.getFeatures().put(
				JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	}

	public JavaGetEventsSocialOutputModel getgeteventssocial() {

		// Return any results in the hypermedia links form.
		return interoperateWithExternalService();
	}

	/*
	 * This function handles the interoperation with the existing 3rd party
	 * service. It calls the according functions to create the input to be sent
	 * to it, to receive its output and if necessary to persist the outcome in
	 * the database. Finally, the result is returned to the client.
	 */
	public JavaGetEventsSocialOutputModel interoperateWithExternalService() {

		// create a new JAX-RS client
		Client oJAXRSRESTClient = Client.create(this.oClientConfiguration);

		// construct the GET query
		WebResource oTargetResource = oJAXRSRESTClient
				.resource(
						"http://109.231.127.61:8080/GetEventsSocial-0.0.1-SNAPSHOT/rest/result/query")
				.queryParam("access_token", this.access_token)
				.queryParam("fields", this.fields)
				.queryParam("units", this.units)
				.queryParam("within", this.within)
				.queryParam("location", this.location)
				.queryParam("include", this.include)
				.queryParam("app_key", this.app_key)
				.queryParam("keywords_num", this.keywords_num);
		ClientResponse oResponse = oTargetResource.accept(
				javax.ws.rs.core.MediaType.APPLICATION_JSON).get(
				ClientResponse.class);
		this.oOutputDataModel = oResponse
				.getEntity(JavaGetEventsSocialOutputModel.class);

		if (isSuccessfullResponseCode(oResponse) == false) {
			throw new WebApplicationException();
		}

		System.out.println("EVENTS: "+ this.oOutputDataModel.getGet_search_response());
		return this.oOutputDataModel;
	}

	/*
	 * This function checks the response code of the server reply against the
	 * known success codes. If the server response HTTP code is within the
	 * success code range, this functions returns true. Otherwise it returns
	 * false
	 */
	private boolean isSuccessfullResponseCode(ClientResponse oResponse) {
		if (oResponse.getStatus() == 200) { // Status OK
			return true;
		} else if (oResponse.getStatus() == 201) { // Status CREATED
			return true;
		} else if (oResponse.getStatus() == 202) { // Status Accepted
			return true;
		} else if (oResponse.getStatus() == 203) { // Status NON-AUTHORITATIVE
													// INFORMATION
			return true;
		} else if (oResponse.getStatus() == 204) { // Status NO CONTENT
			return true;
		} else if (oResponse.getStatus() == 205) { // Status RESET CONTENT
			return true;
		} else if (oResponse.getStatus() == 206) { // Status PARTIAL CONTENT
			return true;
		}

		return false;
	}

}
