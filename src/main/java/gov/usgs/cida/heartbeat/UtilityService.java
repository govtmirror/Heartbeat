package gov.usgs.cida.heartbeat;

//import gov.usgswim.biodata.dao.intfc.IQuickSearchDao;
//import gov.usgswim.biodata.domain.HeartBeat;
//import gov.usgswim.biodata.domain.QuickSearchResult;
//import gov.usgswim.biodata.domain.User;
//import gov.usgswim.biodata.service.intfc.IUserService;
//import gov.usgswim.biodata.service.intfc.IUtilityService;
//import gov.usgswim.biodata.util.BiodataSupportRequest;
//import gov.usgswim.biodata.util.BiodataUtil;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Hashtable;
import java.util.Map;

//import javax.mail.internet.MimeMessage;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.velocity.app.VelocityEngine;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.mail.javamail.MimeMessagePreparator;
//import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * Implementation of the IUtilityService which provides a set of
 * services that are not necessarily associated with the business
 * domain but might provide technical services to the application.
 * 
 * @author srlewein
 *
 */
public class UtilityService {
//implements IUtilityService {
//
//	private static DecimalFormat DISK_SPACE_FORMAT = new DecimalFormat("0.0#");
//	private static double KB_CONVERSION = 1024.0;
//	private static double MB_CONVERSION = 1024 * 1024.0;
//	private static double GB_CONVERSION = 1024 * 1024 * 1024.0;
//
//	private static String[] CONNECTION_POOLS_TO_TEST = new String[] {"biodata", "bioshare", "biostaging", "biotdb"};
//	private static String CONNECTION_POOL_SERVICE = "ManagedConnectionPool";
//	private static long FREESPACE_THRESHOLD_BYTES = 1 * 1024 * 1024 * 1024;		// 1GB
//	private static int AVAILABLE_CONNECTION_THRESHOLD = 0;
//
//	/** used to send the email. */
//	private JavaMailSender mailSender;
//	
//	/** Spring-injected velocity engine. */
//	private VelocityEngine velocityEngine;
//
//	/** Logger for class. */
//	private static final Log LOG = LogFactory.getLog(UtilityService.class);
//	
//	/** Used to check ldap/AD connectivity. */
//	private IUserService userService;
//	/**
//	 * {@inheritDoc} 
//	 * @see gov.usgswim.biodata.service.intfc.IUtilityService#heartBeat()
//	 */
//	@Override
//	public HeartBeat heartBeat() {
//
//		HeartBeat heartBeat = new HeartBeat();
//		heartBeat.setHealthy(true);
//		checkLocalDiskSpace(heartBeat);
//		checkDatabase(heartBeat);
//		checkActiveDirectory(heartBeat);
////		checkEtl(heartBeat);
////		checkRetrievalSystem(heartBeat);
//		return heartBeat;
//	}
//	
//	/**
//	 * Performs a simple query to determine if db is working properly.
//	 * 
//	 * @param heartBeat .
//	 */
//	private void checkDatabase(final HeartBeat heartBeat) {
//		IQuickSearchDao dao =  QuickSearchResult.getDao();
//		try {
//			dao.executeQuickSearch("DUDE", null);
//		} catch (Exception e) {
//			heartBeat.setHealthy(false);
//			heartBeat.addMessage("A database problem was encountered: " + e.getMessage());
//			LOG.error("Database problem encountered.", e);
//		}
//	}
//	
//	/**
//	 * Performs check to see if active directory is reachable.
//	 * 
//	 * @param heartBeat .
//	 */
//	private void checkActiveDirectory(final HeartBeat heartBeat) {
//		
//		try {
//			User user = userService.searchForUser("testuser");
//			if (null == user) {
//				heartBeat.setHealthy(false);
//				heartBeat.addMessage("Unable to find testuser in active directory.");
//				LOG.error("Unable to find testuser in active directory.");
//			}
//		} catch (Exception e) {
//			heartBeat.setHealthy(false);
//			heartBeat.addMessage("A problem was encountered trying to reach active directory: " + e.getMessage());
//			LOG.error("Trouble reaching active directory.", e);
//		}
//	}
//	
//	/** {@inheritDoc} 
//	 * @see gov.usgswim.biodata.service.intfc.IUtilityService#sendSupportRequest(gov.usgswim.biodata.util.BiodataSupportRequest)
//	 */
//	@Override
//	public void sendSupportRequest(final BiodataSupportRequest inSupportRequest) {
//		if (inSupportRequest != null) {
//			final String reportingEmail; 
//			if (inSupportRequest.getReportingUser() != null && inSupportRequest.getReportingUser().getAdFlag()) {
//					reportingEmail = inSupportRequest.getReportingUser().getEmail(); 
//			} else {
//				reportingEmail = BiodataUtil.getDefaultEmail();
//			}
//			MimeMessagePreparator preparator = new MimeMessagePreparator() {
//				public void prepare(final MimeMessage mimeMessage) throws Exception {
//					MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
//					message.setTo(inSupportRequest.getRouting());
//					message.setFrom(reportingEmail);
//					message.setReplyTo(reportingEmail);
//					message.setSubject(inSupportRequest.getMessageSubject());
//					Map<String, String> model = inSupportRequest.getTemplateParameters();
//					String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, 
//							"gov/usgswim/biodata/templates/SupportRequestEmailTemplate.html", model);
//					message.setText(text, true);
//				}
//			};
//			this.mailSender.send(preparator);
//		}
//	}
//	
//	/**
//	 * Checks the ETL tier to make sure it is up and running.
//	 * 
//	 * @param heartBeat .
//	 */
//	private void checkEtl(HeartBeat heartBeat) {
//		try {
//			MBeanServerConnection server = connectToJMXServer("jnp://igsarmewwbbio1.er.usgs.gov:1199");	// TODO: read appropriate URL from environment (this is prod ETL)
//
//			for (String pool: CONNECTION_POOLS_TO_TEST) {
//				ObjectName poolObject = createObjectNameFor("jboss.jca", "service", CONNECTION_POOL_SERVICE, "name", pool);
//				verifyConnectionPool(server, poolObject, heartBeat);
//			}
//		} catch (Exception e) {
//			heartBeat.setHealthy(false);
//			heartBeat.addMessage("Unable to contact ETL JMX instance: " + e);
//		}
//	}
//
//	/**
//	 * Checks the bioshare system to determine whether or not 
//	 * bioshare is running.
//	 * 
//	 * @param heartBeat .
//	 */
//	private void checkRetrievalSystem(final HeartBeat heartBeat) {
//		//TODO: need to implement
//	}
//
//	/**
//	 * Checks the free space on the local disk and raises an error if
//	 * either the file system is inaccessible or the free space is below a given threshold.
//	 * @param heartBeat
//	 */
//	private void checkLocalDiskSpace(final HeartBeat heartBeat) {
//		File tempFile = null;
//		try {
//			tempFile = createTempFile();
//			verifyDiskSpace(tempFile, heartBeat);
//		} catch (IOException ioe) {
//			heartBeat.setHealthy(false);
//			heartBeat.addMessage("Unable to create temp file: " + ioe);
//		} finally {
//			if (tempFile != null) {
//				tempFile.delete();
//			}
//		}
//	}
//
//	/**
//	 * @param inUserService the userService to set
//	 */
//	public void setUserService(final IUserService inUserService) {
//		this.userService = inUserService;
//	}
//	
//	/**
//	 * @param inMailSender the mailSender to set
//	 */
//	public void setMailSender(final JavaMailSender inMailSender) {
//		mailSender = inMailSender;
//	}
//	
//	/**
//	 * @param inVelocityEngine the velocityEngine to set
//	 */
//	public void setVelocityEngine(final VelocityEngine inVelocityEngine) {
//		velocityEngine = inVelocityEngine;
//	}
//
//	private void verifyDiskSpace(final File tempFile, final HeartBeat heartBeat) {
//		long usableSpaceBytes = tempFile.getUsableSpace();
//		if (usableSpaceBytes < FREESPACE_THRESHOLD_BYTES) {
//			heartBeat.setHealthy(false);
//			heartBeat.addMessage("Free disk space below threshold of " + formatBytesForDisplay(FREESPACE_THRESHOLD_BYTES));
//		}
//		String availableSpace = formatBytesForDisplay(usableSpaceBytes);
//		String totalSpace = formatBytesForDisplay(tempFile.getTotalSpace());
//		heartBeat.addMessage("Usable free disk space: " + availableSpace + " of " + totalSpace);
//	}
//
//	/**
//	 * Formats a given value in bytes for readability in a sensible unit.
//	 * @param bytes the raw value in bytes
//	 * @return the formatted output
//	 */
//	private String formatBytesForDisplay(long bytes) {
//		if (bytes >= GB_CONVERSION) {
//			return DISK_SPACE_FORMAT.format(bytes / GB_CONVERSION) + "G";
//		} else
//		if (bytes >= MB_CONVERSION) {
//			return DISK_SPACE_FORMAT.format(bytes / MB_CONVERSION) + "M";
//		} else
//		if (bytes >= KB_CONVERSION) {
//			return DISK_SPACE_FORMAT.format(bytes / KB_CONVERSION) + "K";
//		} else
//		{
//			return bytes + "B";
//		}
//	}
//
//	/**
//	 * Creates a temporary file in the default temp location on the file system.
//	 * @return
//	 * @throws IOException
//	 */
//	private File createTempFile() throws IOException {
//		File tempFile = File.createTempFile("biodata_freespace_check_", "");
//		LOG.debug("created temp file at " + tempFile.getAbsolutePath());
//		return tempFile;
//	}
//
//	/**
//	 * Creates an RMI connection to the specified JMX server
//	 * @param url the location of the JMX server
//	 * @return an MBeanServerConnection
//	 * @throws NamingException
//	 */
//	private MBeanServerConnection connectToJMXServer(String url) throws NamingException {
//		Hashtable<String, String> env = new Hashtable<String, String>();
//		String factory="org.jnp.interfaces.NamingContextFactory";
//		env.put(Context.INITIAL_CONTEXT_FACTORY, factory);
//		env.put(Context.PROVIDER_URL, url);
//		InitialContext ctx = new InitialContext(env);
//
//		MBeanServerConnection server = (MBeanServerConnection) ctx.lookup("jmx/invoker/RMIAdaptor");
//
//		return server;
//	}
//
//	/**
//	 * Constructs a JMX object name for the given domain and list of keys
//	 * @param domain the JMX domain
//	 * @param values the key/value properties that comprise the name (alternating key, value, key, value...)
//	 * @return an ObjectName referring to the JMX object
//	 * @throws MalformedObjectNameException
//	 */
//	private ObjectName createObjectNameFor(String domain, String... values) throws MalformedObjectNameException {
//		if (values.length % 2 != 0) {
//			throw new IllegalArgumentException("values must be supplied in pairs (key, value)");
//		}
//		Hashtable<String, String> keys = new Hashtable<String, String>();
//		for (int i = 0; i < values.length; i += 2) {
//			keys.put(values[i], values[i + 1]);
//		}
//		ObjectName name = new ObjectName(domain, keys);
//		return name;
//	}
//
//	/**
//	 * Verifies the availability of connections in the given connection pool.
//	 * @param server the JMX server to query
//	 * @param connectionPool the JMX object name of the connection pool to query
//	 * @param heartBeat the accumulated status object
//	 */
//	private void verifyConnectionPool(MBeanServerConnection server, ObjectName connectionPool, HeartBeat heartBeat) {
//		try {
//			int inUseConnectionCount = Integer.parseInt(server.getAttribute(connectionPool, "InUseConnectionCount").toString());
//			int availableConnectionCount = Integer.parseInt(server.getAttribute(connectionPool, "AvailableConnectionCount").toString());
//			int maxSize = Integer.parseInt(server.getAttribute(connectionPool, "MaxSize").toString());
//	
//			if (availableConnectionCount <= AVAILABLE_CONNECTION_THRESHOLD) {
//				heartBeat.setHealthy(false);
//				heartBeat.addMessage("ETL connection pool " + connectionPool + " is exhausted:"
//						+ " available=" + availableConnectionCount
//						+ " inUse=" + inUseConnectionCount
//						+ " maxSize=" + maxSize);
//			}
//		} catch (Exception e) {
//			heartBeat.setHealthy(false);
//			heartBeat.addMessage("Error while querying connection pool MBean " + connectionPool + ": " + e);
//		}
//	}
}
