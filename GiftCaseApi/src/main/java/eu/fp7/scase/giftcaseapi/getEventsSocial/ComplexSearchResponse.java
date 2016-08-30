package eu.fp7.scase.giftcaseapi.getEventsSocial;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComplexSearchResponse {
	
	@JsonProperty("events")
	private ComplexEvents events;
	
	/* There follows a list of setter and getter functions.*/
	public void setEvents(ComplexEvents events){
    	this.events = events;
    }
	
	public ComplexEvents getEvents(){
        return this.events;
    }

}
