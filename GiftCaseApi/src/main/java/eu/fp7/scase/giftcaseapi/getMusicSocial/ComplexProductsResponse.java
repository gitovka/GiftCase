package eu.fp7.scase.giftcaseapi.getMusicSocial;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import eu.fp7.scase.giftcaseapi.getEventsSocial.ComplexEvent;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComplexProductsResponse {
	
	@JsonProperty("products")
	private List<ComplexProducts> products;
	
	/* There follows a list of setter and getter functions.*/
	public void setProducts(List<ComplexProducts> products){
    	this.products = products;
    }
	
	public List<ComplexProducts> getProducts(){
        return this.products;
    }

	

}
