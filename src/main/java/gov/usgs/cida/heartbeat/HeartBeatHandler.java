package gov.usgs.cida.heartbeat;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

public class HeartBeatHandler extends HttpServlet {
	Check check; // populate by spring config or web.xml config
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HeartBeat hb = check.getHeartBeat();
		serializeHeartBeat(hb, resp.getOutputStream());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	/*
	 * TODO override these to read in config parameters from web.xml or spring
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
	}
	*/
	
	public void serializeHeartBeat(HeartBeat h, OutputStream out) {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(HeartBeat.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(h, out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
	
}
