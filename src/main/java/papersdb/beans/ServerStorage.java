package papersdb.beans;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;;

public class ServerStorage {

	private static final String SLASH = "/";
	private String requestsDir;
	private String papersDir;
	private String serverPapersUploadDir;

	public String getRequestsDir() {
		return requestsDir;
	}

	public String getPapersDir() {
		return papersDir;
	}
	
	public String getServerPapersUploadDir() {
		return serverPapersUploadDir;
	}

	public ServerStorage(String baseDirectory) {
		if (!baseDirectory.endsWith(SLASH)) {
			baseDirectory = baseDirectory + SLASH;
		}
		requestsDir = baseDirectory + "requests/";
		papersDir = baseDirectory + "papers/";
		serverPapersUploadDir = baseDirectory + "serverPapersUpload/";

		File file = new File(requestsDir);
		file.mkdirs();
		file = new File(papersDir);
		file.mkdirs();
	}

	public void uploadFiles(String directory, List<MultipartFile> multipartFiles) throws IOException {
		if (!directory.endsWith(SLASH)) {
			directory = directory + SLASH;
		}
		File file = new File(directory);
		file.mkdirs();
		for (MultipartFile multipartFile : multipartFiles) {
			file = new File(directory + multipartFile.getOriginalFilename());
			IOUtils.copy(multipartFile.getInputStream(), new FileOutputStream(file));
		}
	}

	public void uploadFile(String absoluteFilePath, MultipartFile multipartFile) throws IOException {
		uploadFile(absoluteFilePath, multipartFile.getInputStream());
	}

	public void uploadFile(String absoluteFilePath, InputStream inputStream) throws IOException {
		IOUtils.copy(inputStream, new FileOutputStream(new File(absoluteFilePath)));
	}

	public byte[] getZip(String directory) throws IOException {

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
		File dir = new File(directory);
		for (File file : dir.listFiles()) {
			zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
			IOUtils.copy(new FileInputStream(file), zipOutputStream);
			zipOutputStream.closeEntry();
		}
		zipOutputStream.flush();
		byteArrayOutputStream.flush();
		zipOutputStream.close();
		byteArrayOutputStream.close();
		return byteArrayOutputStream.toByteArray();
	}

	public File getFirstFileFromDirectory(String directory) throws FileNotFoundException {
		File file = new File(directory);
		File[] files = file.listFiles();
		if (files.length == 0) {
			return null;
		} else {
			return files[0];
		}
	}

	public int getNumberOfFiles(String directory) {
		File file = new File(directory);
		return file.list().length;
	}

	public String getContentType(File file) {
		return new MimetypesFileTypeMap().getContentType(file);
	}

	public void removeDirectory(String dirPath) throws IOException {
		// Deleting directory recursively
		FileUtils.deleteDirectory(new File(dirPath));
	}

}
