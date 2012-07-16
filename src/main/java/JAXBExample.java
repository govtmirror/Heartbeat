
import gov.usgs.cida.heartbeat.HeartBeat;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class JAXBExample {
	private static final Class<HeartBeat> XMLClass = HeartBeat.class; //Customer.class;

	public static void main(String[] args) {
		/*
		  Customer customer = new Customer();
		  customer.setId(100);
		  customer.setName("mkyong");
		  customer.setAge(29);
		 */
		HeartBeat h = new HeartBeat();
		{
			h.setHealthy(true);
			h.addMessage("Hello Health");
			h.addDetail("key", "value", "service", 1.0);
			
		}
		try {

			File file = new File("C:\\Users\\ikuoikuo\\Desktop\\analysis\\file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(XMLClass);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(h, file);
			jaxbMarshaller.marshal(h, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
}