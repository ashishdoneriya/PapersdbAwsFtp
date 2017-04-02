package papersdb.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPManager2 {

	private static final String SLASH = "/";
	
	private static final String EMPTY = "";
	
	private String host;

	private String username;

	private String password;

	public FTPManager2(String host, String username, String password) {
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
		ftp.storeFile(hostDir + fileName, inputStream);
		disconnect(ftp);
	}
	
	public void rename(String from, String to)
			throws Exception {
		FTPClient ftp = getFTPClient();
		//createDirectories(target, ftp);
		//ftp.storeFile(hostDir + fileName, inputStream);
		ftp.rename(from, to);
		disconnect(ftp);
	}

	private void createDirectories(String hostDir, FTPClient ftp) throws IOException {
		if (hostDir.equals("/")) {
			return;
		}
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

	private FTPClient getFTPClient() throws Exception {
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
	public static void main(String[] args) throws Exception {
		System.out.println("Start");
		FTPManager ftpUploader = new FTPManager("ftp.csetutorials.com", "collegeexampapers@csetutorials.com", "manitpapers");
		//FTP server path is relative. So if FTP account HOME directory is "/home/pankaj/public_html/" and you need to upload 
		// files to "/home/pankaj/public_html/wp-content/uploads/image2/", you should pass directory parameter as "/wp-content/uploads/image2/"
		InputStream input = new FileInputStream(new File("/home/ashish/Downloads/BookPalace/src/main/java/in/co/impetus/classes/Column.java"));
		ftpUploader.uploadFile("/sdad/sadsd/fdgfdg/gfgfhgf//", "co11", input);
		System.out.println("Done");
//		System.out.println("Start");
//		FTPManager2 ftpUploader = new FTPManager2("ftp.csetutorials.com", "collegeexampapers@csetutorials.com", "manitpapers");
//		//FTP server path is relative. So if FTP account HOME directory is "/home/pankaj/public_html/" and you need to upload 
//		// files to "/home/pankaj/public_html/wp-content/uploads/image2/", you should pass directory parameter as "/wp-content/uploads/image2/"
//		InputStream input = new FileInputStream(new File("/home/ashish/Downloads/BookPalace/src/main/java/in/co/impetus/classes/Column.java"));
//		ftpUploader.uploadFile("/a", "column", input);
//	//	ftpUploader.rename("/", "");
//		System.out.println("Done");
	}

}
