package gov.usgs.cida.heartbeat;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



/**
 * An object that can be serialized to provide a system status. It is
 * intended to be the object that delivers health information about the
 * system and is jaxb enabled so that it can be easily serialized
 * by Web Service layer.
 * 
 * @author srlewein
 *
 */
@XmlRootElement
@XmlType(propOrder = { "healthy", "messages", "details" })
public class HeartBeat {
	
	/** True if system checks indicate system is working as expected. */
	private boolean healthy;
	
	/** A list of optional messages that can be used to debug problems. */
	private final List<String> messages = new ArrayList<String>();
	
	private List<Detail> details = new ArrayList<Detail>();
	
	/**
	 * @return the healthy
	 */
	@XmlElement(name = "healthy")
	public boolean isHealthy() {
		return healthy;
	}

	/**
	 * @param inHealthy the healthy to set
	 */
	public void setHealthy(final boolean inHealthy) {
		this.healthy = inHealthy;
	}

	/**
	 * @return the messages
	 */
	@XmlElementWrapper
	@XmlElement(name = "message", type = String.class)
	public List<String> getMessages() {
		return messages;
	}
	
	/**
	 * Add a message to the HeartBeat.
	 * @param inMessage to add to the messages.
	 */
	public void addMessage(final String inMessage) {
		messages.add(inMessage);
	}
	
	public void addDetail(String key, String value, String type, Double measure) {
		details.add(new Detail(key, value, type,  measure));
	}
	
	@XmlElementWrapper
	@XmlElement(name = "detail", type = Detail.class)
	public List<Detail> getDetails(){
		return details;
	}

	@Override
	public String toString() {
		return "";
	}
	
	public String toJSON() {
		return "";
	}
}
