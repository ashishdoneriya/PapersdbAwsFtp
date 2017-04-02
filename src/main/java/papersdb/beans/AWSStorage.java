package papersdb.beans;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;;

public class AWSStorage {
	
	private static final String SLASH = "/";

	public String bucketName;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private AmazonS3 amazonS3;
	
	public AWSStorage(String bucketName) {
		this.bucketName = bucketName;
	}
	
	public InputStream downloadFile(String fileName) throws IOException {
		String filePath = "s3://" + bucketName + SLASH + fileName;
		Resource resource = resourceLoader.getResource(filePath);
		return resource.getInputStream();
	}
	
	public String uploadFile(String fileName, MultipartFile file) throws IOException {
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(file.getContentType());
		metadata.setContentDisposition("attachment; filename=" + fileName);
		
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, 
				file.getInputStream(), metadata).withCannedAcl(CannedAccessControlList.PublicRead);
		
		amazonS3.putObject(putObjectRequest);
		return getURL(fileName);
	}
	
	public String uploadFile(String fileName, String contentType,
			InputStream inputStream) throws IOException {
		
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(contentType);
		metadata.setContentDisposition("attachment; filename=" + fileName);
		PutObjectRequest putObjectRequest = new PutObjectRequest(
					bucketName, fileName, inputStream, metadata)
				.withCannedAcl(CannedAccessControlList.PublicRead);
		
		amazonS3.putObject(putObjectRequest);
		return getURL(fileName);
	}
	
	public String getURL(String fileName) {
		GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucketName, fileName);
		String url = amazonS3.generatePresignedUrl(urlRequest).toString();
		url = url.substring(0, url.indexOf('?'));
		return url;
	}

	public void uploadFileUsingSpringCloud(String fileName, InputStream inputStream)
			throws IOException {
		String filePath = "s3://" + bucketName + SLASH + fileName;
		Resource resource = resourceLoader.getResource(filePath);
		WritableResource writableResource = (WritableResource) resource;
		IOUtils.copy(inputStream, writableResource.getOutputStream());
	}
}
