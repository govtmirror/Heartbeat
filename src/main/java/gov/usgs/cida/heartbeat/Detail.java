package gov.usgs.cida.heartbeat;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Detail{
	
	private String key;
	private String value;
	private Double measure;
	private String type;
	
	public Detail() {};
	
	public Detail(String key, String value, String type, Double measure) {
		this.key = key;
		this.value = value;
		this.type = type;
		this.measure = measure;
	}
	
	/* ===================
	 * GETTERS and SETTERS
	 =====================*/
	@XmlAttribute
	public String getKey() { return key;}
	public void setKey(String key) { this.key = key;}
	
	@XmlElement
	public String getValue() { return value;}
	public void setValue(String value) { this.value = value;}
	
	@XmlAttribute
	public String getType() { return type;}
	public void setType(String type) { this.type = type;}
	
	@XmlAttribute
	public Double getMeasure() { return measure;}
	public void setMeasure(Double measure) { 
		if (measure != null && measure >= 0.0 && measure <= 1.0) {
			this.measure = measure;
		} else {
			throw new IllegalArgumentException("measure must be between 0.0 and 1.0, inclusive");
		}
	}
	
}