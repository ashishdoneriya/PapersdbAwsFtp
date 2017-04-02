package papersdb.beans;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.multipart.MultipartFile;

public class FTPManager {

	private static final String SLASH = "/";
	
	private static final String EMPTY = "";
	
	private String host;

	private String username;

	private String password;

	public FTPManager(String host, String username, String password) {
		this.host = host;
		this.username = username;
		this.password = password;
	}

	public void uploadFile(String hostDir, String fileName, InputStream inputStream)
			throws Exception {
		FTPClient ftp = getFTPClient();
		if (! hostDir.endsWith(SLASH)) {
			hostDir = hostDir + SLASH;
		}
		createDirectories(hostDir, ftp);
		ftp.storeFile(hostDir + fileName.replaceAll(" ", "_"), inputStream);
		disconnect(ftp);
	}
	
	public void removeDirectory(String directory) throws Exception {
		FTPClient ftp = getFTPClient();
		ftp.removeDirectory(directory);
		disconnect(ftp);
	}
	
	public void uploadMultipleFile(String hostDir, List<MultipartFile> files) throws Exception {
		FTPClient ftp = getFTPClient();
		if (! hostDir.endsWith(SLASH)) {
			hostDir = hostDir + SLASH;
		}
		createDirectories(hostDir, ftp);
		for (MultipartFile file: files) {
			ftp.storeFile(hostDir + file.getOriginalFilename().replaceAll(" ", "_"),
					file.getInputStream());
		}
		disconnect(ftp);
	}

	public void createDirectories(String hostDir, FTPClient ftp) throws IOException {
		if (hostDir.startsWith(SLASH)) {
			hostDir = hostDir.substring(1);
		}
		String[] arr = hostDir.split(SLASH);
		String currDir = EMPTY;
		for(String dir:arr) {
			currDir = currDir + SLASH + dir;
			if (ftp.mlistFile(currDir) == null ) {
				ftp.makeDirectory(currDir);
			}
		}
	}



	public FTPClient getFTPClient() throws Exception {
		FTPClient ftp = new FTPClient();
		ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
		int reply;
		ftp.connect(host);
		reply = ftp.getReplyCode();
		if (! FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			throw new Exception("Exception in connecting to FTP Server");
		}
		ftp.login(username, password);
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
		ftp.enterLocalPassiveMode();
		return ftp;
	}

	private void disconnect(FTPClient ftp){
		if (ftp.isConnected()) {
			try {
				ftp.logout();
				ftp.disconnect();
			} catch (IOException f) {
				// do nothing as file is already saved to server
			}
		}
	}
	
//	@ResponseBody
//	@RequestMapping(value="/downloadzip/file_{requestID}.zip", method=RequestMethod.GET)
//	public void downloadZip(@PathVariable("requestID") String requestID,
//			HttpServletResponse response) throws Exception {
//		String zipFileName = "file_" + requestID+ ".zip";
//		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//		ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
//
//		String directory = "/tmp/"+ requestID +"/";
//		FTPClient ftp = ftpManager.getFTPClient();
//		FTPFile[] files = ftp.listFiles(directory);
//		for (FTPFile file: files) {
//			zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
//			ftp.retrieveFile(directory + file.getName(), zipOutputStream);
//			zipOutputStream.closeEntry();
//		}
//		zipOutputStream.flush();
//		byteArrayOutputStream.flush();
//			zipOutputStream.close();
//			byteArrayOutputStream.close();
//		byte[] zip = byteArrayOutputStream.toByteArray();
//		response.setHeader("Content-Disposition", "attachment; filename=" + zipFileName);
//		OutputStream out = response.getOutputStream();
//		out.write(zip);
//		out.flush();
//		out.close();
//	}

}
