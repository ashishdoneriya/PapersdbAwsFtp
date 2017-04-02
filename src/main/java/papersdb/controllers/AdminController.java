package papersdb.controllers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;

import papersdb.beans.ServerStorage;
import papersdb.dao.BranchDao;
import papersdb.dao.CollegeDao;
import papersdb.dao.PaperDao;
import papersdb.dao.PaperSubmitRequestDao;
import papersdb.dao.SubjectDao;
import papersdb.model.Branch;
import papersdb.model.College;
import papersdb.model.Paper;
import papersdb.model.PaperSubmitRequest;
import papersdb.model.Subject;
import papersdb.model.User;
import papersdb.utils.PaperThread;

@Controller
public class AdminController {

	@Autowired
	private PaperDao paperDao;
	@Autowired
	private BranchDao branchDao;
	@Autowired
	private CollegeDao collegeDao;
	@Autowired
	private SubjectDao subjectDao;
	@Autowired
	private ServerStorage serverStorage;
	@Autowired
	private PaperSubmitRequestDao paperSubmitRequestDao;

	private static final Logger LOG = Logger.getLogger(AdminController.class);

	@RequestMapping(value = "home.html")
	public String getIndex(Model model, HttpSession session) {
		LOG.debug("Inside AdminController.getIndex()");
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "index";
		}
		if (user.getEmail().equalsIgnoreCase("ashish") && user.getPassword().equalsIgnoreCase("a")) {
			model.addAttribute("requests", paperSubmitRequestDao.list());
			return "home";
		}
		return "index";
	}

	@ResponseBody
	@RequestMapping(value = "/getrequest/{requestID}", method = RequestMethod.GET)
	public String getRequest(@PathVariable("requestID") String requestID) {
		LOG.debug("Inside AdminController.getRequest(), requestID = " + requestID);
		Gson gson = new Gson();
		return gson.toJson(paperSubmitRequestDao.get(Integer.parseInt(requestID)));
	}

	@ResponseBody
	@RequestMapping(value = "/deleterequest/{requestID}", method = RequestMethod.POST)
	public String deleteRequest(@PathVariable("requestID") String requestID) {
		LOG.debug("Inside AdminController.deleteRequest(), requestID = " + requestID);
		try {
			serverStorage.removeDirectory(serverStorage.getRequestsDir() + requestID);
			paperSubmitRequestDao.delete(paperSubmitRequestDao.get(Integer.parseInt(requestID)));
		} catch (IOException e) {
			LOG.error("Error while deleting request.", e);
			return "error";
		}
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/downloadzip/file_{requestID}.zip", method = RequestMethod.GET)
	public void downloadZip(@PathVariable("requestID") String requestID, HttpServletResponse response) {
		LOG.debug("Inside AdminController.downloadZip(), requestID = " + requestID);
		String zipFileName = "request" + requestID + ".zip";

		response.setHeader("Content-Disposition", "attachment; filename=" + zipFileName);
		try {
			OutputStream out = response.getOutputStream();
			out.write(serverStorage.getZip(serverStorage.getRequestsDir() + requestID));
			out.flush();
			out.close();
		} catch (IOException e) {
			LOG.error("Error while downloading zip.", e);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/addPaperFromFolder", method = RequestMethod.POST)
	public String addPapersFromFileSystem() {
		PaperThread thread = new PaperThread();
		thread.start();
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/addPaperDirectly", method = RequestMethod.POST)
	public String addPaperDirectly(MultipartHttpServletRequest request) {
		LOG.debug("Inside AdminController.addPaperDirectly()");

		String collegeName = request.getParameter("college");
		String branchName = request.getParameter("branch");
		String subjectName = request.getParameter("subject");
		String month = request.getParameter("month");
		String sYear = request.getParameter("year");
		String description = request.getParameter("description");
		MultipartFile file = request.getFile("file");

		LOG.debug("collegeName = " + collegeName);
		LOG.debug("branchName = " + branchName);
		LOG.debug("subjectName = " + subjectName);
		LOG.debug("month = " + month);
		LOG.debug("sYear = " + sYear);
		LOG.debug("description = " + description);
		LOG.debug("file = " + file);

		if (collegeName == null || collegeName.trim().isEmpty() || branchName == null || branchName.trim().isEmpty()
				|| subjectName == null || subjectName.trim().isEmpty() || file == null) {
			return "error";
		}
		College college = collegeDao.get(collegeName);
		if (college == null) {
			college = new College(collegeName);
			collegeDao.save(college);
		}
		Branch branch = branchDao.get(branchName);
		if (branch == null) {
			branch = new Branch(branchName, college);
			branchDao.save(branch);
		}
		Subject subject = subjectDao.get(subjectName);
		if (subject == null) {
			subject = new Subject(subjectName, branch);
			subjectDao.save(subject);
		}

		if (file != null) {
			Paper paper = new Paper(subject);
			if (description != null && !description.trim().isEmpty()) {
				paper.setDescription(description);
			}
			if (sYear != null && !sYear.trim().isEmpty()) {
				paper.setYear(Integer.parseInt(sYear));
				if (month != null && !month.isEmpty()) {
					paper.setMonth(month);
				}
			}
			paperDao.save(paper);
			String fileName = createFileName(paper) + getExtension(file.getOriginalFilename());
			try {
				serverStorage.uploadFile(serverStorage.getPapersDir() + fileName, file);
				paper.setPath(fileName);
				paperDao.update(paper);
			} catch (IOException e) {
				LOG.error("Error while uploading file to AWS.", e);
			}
		}
		return "success";
	}

	public String createFileName(Paper paper) {
		StringBuffer fileName = new StringBuffer(paper.getSubject().getName());
		fileName.append(" (");
		if (paper.getMonth() != null) {
			fileName.append(paper.getMonth()).append(" ");
		}
		if (paper.getYear() != null) {
			fileName.append(paper.getYear()).append(" ");
		}
		String description = paper.getDescription();
		if (description != null && !description.trim().isEmpty()) {
			fileName.append(description);
		}
		fileName.append(") ").append(paper.getPaperID());
		return fileName.toString().replace("() ", "");
	}

	@ResponseBody
	@RequestMapping(value = "/approvepaper", method = RequestMethod.POST)
	public String approvePaper(MultipartHttpServletRequest request) {
		LOG.debug("Inside AdminController.approvePaper()");

		String requestID = request.getParameter("requestID");
		String collegeName = request.getParameter("college");
		String branchName = request.getParameter("branch");
		String subjectName = request.getParameter("subject");
		String month = request.getParameter("month");
		String sYear = request.getParameter("year");
		String description = request.getParameter("description");
		MultipartFile file = request.getFile("file");

		LOG.debug("requestID = " + requestID);
		LOG.debug("collegeName = " + collegeName);
		LOG.debug("branchName = " + branchName);
		LOG.debug("subjectName = " + subjectName);
		LOG.debug("month = " + month);
		LOG.debug("sYear = " + sYear);
		LOG.debug("description = " + description);
		LOG.debug("file = " + file);

		if (collegeName == null || collegeName.trim().isEmpty() || branchName == null || branchName.trim().isEmpty()
				|| subjectName == null || subjectName.trim().isEmpty()) {
			return "error";
		}
		College college = collegeDao.get(collegeName);
		if (college == null) {
			college = new College(collegeName);
			collegeDao.save(college);
		}
		Branch branch = branchDao.get(branchName);
		if (branch == null) {
			branch = new Branch(branchName, college);
			branchDao.save(branch);
		}
		Subject subject = subjectDao.get(subjectName);
		if (subject == null) {
			subject = new Subject(subjectName, branch);
			subjectDao.save(subject);
		}

		Paper paper = new Paper(subject);
		if (description != null && !description.trim().isEmpty()) {
			paper.setDescription(description);
		}
		if (sYear != null && !sYear.trim().isEmpty()) {
			paper.setYear(Integer.parseInt(sYear));
			if (month != null && !month.isEmpty()) {
				paper.setMonth(month);
			}
		}
		paperDao.save(paper);
		PaperSubmitRequest paperSubmitRequest = paperSubmitRequestDao.get(Integer.parseInt(requestID));
		try {
			String requestsDir = serverStorage.getRequestsDir();
			String fileName = createFileName(paper);
			if (file != null) {
				serverStorage.uploadFile(serverStorage.getPapersDir() + fileName, file);
				fileName = fileName + getExtension(file.getOriginalFilename());
			} else {
				String directory = String.valueOf(paperSubmitRequest.getRequestID());
				// String contentType = null;
				InputStream inputStream = null;
				if (serverStorage.getNumberOfFiles(requestsDir + directory) == 1) {
					File temp = serverStorage.getFirstFileFromDirectory(requestsDir + directory);
					// contentType = serverStorage.getContentType(temp);
					inputStream = new FileInputStream(temp);
					fileName = fileName + getExtension(temp.getName());
				} else {
					byte[] bytes = serverStorage.getZip(requestsDir + directory);
					inputStream = new ByteArrayInputStream(bytes);
					// contentType = "application/zip";
					fileName = fileName + ".zip";
				}
				serverStorage.uploadFile(serverStorage.getPapersDir() + fileName, inputStream);
				inputStream.close();
			}
			paper.setPath(fileName);
			paperDao.update(paper);
			serverStorage.removeDirectory(requestsDir + paperSubmitRequest.getRequestID());
			paperSubmitRequestDao.delete(paperSubmitRequest);
		} catch (IOException e) {
			LOG.error("Error while approving paper.", e);
			return "error";
		}
		return "success";
	}

	public String getExtension(String fileName) {
		if (fileName == null || fileName.trim().isEmpty()) {
			return "";
		}
		int index = fileName.lastIndexOf('.');
		if (index < 0) {
			return "";
		}
		return fileName.substring(index, fileName.length());
	}

}
