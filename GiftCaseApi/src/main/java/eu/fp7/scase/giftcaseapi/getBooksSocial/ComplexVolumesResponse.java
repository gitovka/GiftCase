package eu.fp7.scase.giftcaseapi.getBooksSocial;


import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComplexVolumesResponse {

	@JsonProperty("items")
	private List<ComplexItems> items;
	
	/* There follows a list of setter and getter functions.*/
	public void setItems(List<ComplexItems> items){
    	this.items = items;
    }
	
	public List<ComplexItems> getItems(){
        return this.items;
    }
}

