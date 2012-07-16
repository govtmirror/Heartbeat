package gov.usgs.cida.heartbeat.checks;

import gov.usgs.cida.heartbeat.HeartBeat;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class DiskSpaceCheck {
	private static DecimalFormat DISK_SPACE_FORMAT = new DecimalFormat("0.0#");
	private static double KB_CONVERSION = 1024.0;
	private static double MB_CONVERSION = 1024 * 1024.0;
	private static long FREESPACE_THRESHOLD_BYTES = 1 * 1024 * 1024 * 1024;		// 1GB
	private static double GB_CONVERSION = 1024 * 1024 * 1024.0;
	
	/**
	 * Checks the free space on the local disk and raises an error if
	 * either the file system is inaccessible or the free space is below a given threshold.
	 * @param heartBeat
	 */
	private void checkLocalDiskSpace(final HeartBeat heartBeat) {
		File tempFile = null;
		try {
			tempFile = createTempFile();
			verifyDiskSpace(tempFile, heartBeat);
		} catch (IOException ioe) {
			heartBeat.setHealthy(false);
			heartBeat.addMessage("Unable to create temp file: " + ioe);
		} finally {
			if (tempFile != null) {
				tempFile.delete();
			}
		}
	}
	
	/**
	 * Creates a temporary file in the default temp location on the file system.
	 * @return
	 * @throws IOException
	 */
	private File createTempFile() throws IOException {
		File tempFile = File.createTempFile("biodata_freespace_check_", "");
		//LOG.debug("created temp file at " + tempFile.getAbsolutePath());
		return tempFile;
	}
	
	private void verifyDiskSpace(final File tempFile, final HeartBeat heartBeat) {
		long usableSpaceBytes = tempFile.getUsableSpace();
		if (usableSpaceBytes < FREESPACE_THRESHOLD_BYTES) {
			heartBeat.setHealthy(false);
			heartBeat.addMessage("Free disk space below threshold of " + formatBytesForDisplay(FREESPACE_THRESHOLD_BYTES));
		}
		String availableSpace = formatBytesForDisplay(usableSpaceBytes);
		String totalSpace = formatBytesForDisplay(tempFile.getTotalSpace());
		heartBeat.addMessage("Usable free disk space: " + availableSpace + " of " + totalSpace);
	}
	
	/**
	 * Formats a given value in bytes for readability in a sensible unit.
	 * @param bytes the raw value in bytes
	 * @return the formatted output
	 */
	private String formatBytesForDisplay(long bytes) {
		if (bytes >= GB_CONVERSION) {
			return DISK_SPACE_FORMAT.format(bytes / GB_CONVERSION) + "G";
		} else
		if (bytes >= MB_CONVERSION) {
			return DISK_SPACE_FORMAT.format(bytes / MB_CONVERSION) + "M";
		} else
		if (bytes >= KB_CONVERSION) {
			return DISK_SPACE_FORMAT.format(bytes / KB_CONVERSION) + "K";
		} else
		{
			return bytes + "B";
		}
	}
}
