package gov.usgswim.biodata.domain;

import gov.usgswim.biodata.util.LazyLoadAccessorFactoryImpl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

import com.sun.xml.bind.XmlAccessorFactory;

/**
 * An object that can be serialized to provide a system status. It is
 * intended to be the object that delivers health information about the
 * system and is jaxb enabled so that it can be easily serialized
 * by Web Service layer.
 * 
 * @author srlewein
 *
 */
@Component("sHeartBeat")
@XmlAccessorFactory(LazyLoadAccessorFactoryImpl.class)
@XmlRootElement
@XmlType(propOrder = { "healthy", "messages" })
public class HeartBeat {
	
	

	/** True if system checks indicate system is working as expected. */
	private boolean healthy;
	
	/** A list of optional messages that can be used to debug problems. */
	private final List<String> messages = new ArrayList<String>();
	
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
	@XmlElement(name = "messages", type = String.class)
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

	@Override
	public String toString() {
		return new ToStringBuilder(this)
					.append("healthy", isHealthy())
					.append("messages", getMessages())
					.toString();
	}
}
