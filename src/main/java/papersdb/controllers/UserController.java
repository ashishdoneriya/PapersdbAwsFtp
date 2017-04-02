package papersdb.controllers;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import papersdb.beans.ServerStorage;
import papersdb.dao.PaperDao;
import papersdb.dao.PaperSubmitRequestDao;
import papersdb.model.Paper;
import papersdb.model.PaperSubmitRequest;

@Controller
public class UserController {
	
	@Autowired private PaperDao paperDao;
	@Autowired private ServerStorage serverStorage;
	@Autowired private PaperSubmitRequestDao paperSubmitRequestDao;
	
	private static final Logger LOG = Logger.getLogger(UserController.class);
	
	@RequestMapping(value="/submit-paper.html", method=RequestMethod.GET)
	public String submitPaperForm() {
		return "submitPaper";
	}
	
	@ResponseBody
	@RequestMapping(value = "/increasecounter/{paperID}", method = RequestMethod.POST)
	public void increaseCounterAndDownloadPaper(@PathVariable("paperID") String paperID,
			HttpServletResponse response) {
		LOG.debug("Inside UserController.increaseCounter(), paperID = " + paperID);
		Paper paper = paperDao.get(Integer.parseInt(paperID));
		paper.increaseCounter();
		paperDao.save(paper);
		
		//Downloading Paper
		response.setHeader("Content-Disposition", "attachment; filename=" + paper.getPath());
		try {
			OutputStream out = response.getOutputStream();
			File file = serverStorage.getFirstFileFromDirectory(serverStorage.getPapersDir() + paper.getPath());
			out.write(FileUtils.readFileToByteArray(file));
			out.flush();
			out.close();
		} catch (IOException e) {
			LOG.error("Error while downloading Paper.", e);
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/downloadPaper/{paperID}", method = RequestMethod.GET)
	public void DownloadPaperAndincreaseCounter(@PathVariable("paperID") String paperID,
			HttpServletResponse response) {
		LOG.debug("Inside UserController.increaseCounter(), paperID = " + paperID);
		Paper paper = paperDao.get(Integer.parseInt(paperID));
		paper.increaseCounter();
		paperDao.save(paper);
		
		//Downloading Paper
		response.setHeader("Content-Disposition", "attachment; filename=" + paper.getPath().replaceAll(" ", "_"));
		try {
			OutputStream out = response.getOutputStream();
			File file = new File(serverStorage.getPapersDir() + paper.getPath());
			out.write(FileUtils.readFileToByteArray(file));
			out.flush();
			out.close();
		} catch (IOException e) {
			LOG.error("Error while downloading Paper.", e);
		}
	}
	
	@ResponseBody
	@RequestMapping(value="submitpapers", method=RequestMethod.POST)
	public String submitPapers(MultipartHttpServletRequest request) throws Exception {
		String college = request.getParameter("college");
		String branch = request.getParameter("branch");
		String subject = request.getParameter("subject");
		String description = request.getParameter("description");
		List<MultipartFile> papers = request.getFiles("papers");
		PaperSubmitRequest paperSubmitRequest = new PaperSubmitRequest(
				college, branch, subject, description);
		paperSubmitRequestDao.save(paperSubmitRequest);
		serverStorage.removeDirectory(serverStorage.getRequestsDir() 
				+ paperSubmitRequest.getRequestID());
		serverStorage.uploadFiles( serverStorage.getRequestsDir() 
				+ paperSubmitRequest.getRequestID(), papers);
		return "success";
	}
	
	@RequestMapping(value="/submitpaper", method=RequestMethod.POST)
	public String submitPaper(MultipartHttpServletRequest request) throws Exception {
		String college = request.getParameter("college");
		String branch = request.getParameter("branch");
		String subject = request.getParameter("subject");
		String description = request.getParameter("description");
		List<MultipartFile> papers = request.getFiles("papers");
		
		PaperSubmitRequest paperSubmitRequest = new PaperSubmitRequest(
				college, branch, subject, description);
		paperSubmitRequestDao.save(paperSubmitRequest);
		
		serverStorage.uploadFiles(serverStorage.getRequestsDir()
				+ paperSubmitRequest.getRequestID(), papers);
		return "redirect:/";
	}
}
