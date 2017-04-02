package papersdb.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import papersdb.dao.BranchDao;
import papersdb.dao.CollegeDao;
import papersdb.dao.PaperDao;
import papersdb.dao.SubjectDao;
import papersdb.model.Branch;
import papersdb.model.College;
import papersdb.model.Paper;
import papersdb.model.Subject;

@Controller
public class ResultController {

	@Autowired private PaperDao paperDao;
	@Autowired private CollegeDao collegeDao;
	@Autowired private BranchDao branchDao;
	@Autowired private SubjectDao subjectDao;
	
	@ResponseBody
	@RequestMapping(value="/papers", method=RequestMethod.GET)
	public String getPapers(HttpServletRequest request) {
		String collegeID = request.getParameter("collegeID");
		String branchID = request.getParameter("branchID");
		String subjectID = request.getParameter("subjectID");
		
		List<Paper> papers = null;
		
		if (subjectID != null && !subjectID.isEmpty()) {
			papers = paperDao.list(subjectID);
		} else if (branchID != null && !branchID.isEmpty()) {
			List<Subject> subjects = subjectDao.list(branchID);
			papers = paperDao.list(subjects);
		} else if (collegeID != null && !collegeID.isEmpty()){
			List<Branch> branches = branchDao.list(collegeID);
			List<Subject> subjects = subjectDao.list(branches);
			papers = paperDao.list(subjects);
		} else {
			papers = paperDao.list();
		}
		Gson gson = new Gson();
		return gson.toJson(papers);
	}
	
	@ResponseBody
	@RequestMapping(value="/subjects", method=RequestMethod.GET)
	public String getSubjects(HttpServletRequest request) {
		String collegeID = request.getParameter("collegeID");
		String branchID = request.getParameter("branchID");
		String branchName = request.getParameter("branchName");
		
		List<Subject> subjects = null;
		if (branchID != null) {
			subjects = subjectDao.list(branchID);
		} else if (collegeID != null){
			List<Branch> branches = branchDao.list(collegeID);
			subjects = subjectDao.list(branches);
		} else if (branchName != null) {
			Branch branch = branchDao.get(branchName);
			subjects = subjectDao.list(branch);
		} else {
			subjects = subjectDao.list();
		}
		Gson gson = new Gson();
		return gson.toJson(subjects);
	}
	
	@ResponseBody
	@RequestMapping(value="/branches", method=RequestMethod.GET)
	public String getBranches(HttpServletRequest request) {
		String collegeID = request.getParameter("collegeID");
		String collegeName = request.getParameter("collegeName");
		
		List<Branch> branches = null;
		if (collegeID != null) {
			branches = branchDao.list(collegeID);
		} else if (collegeName != null) {
			College college = collegeDao.get(collegeName);
			branches = branchDao.list(college);
		} else {
			branches = branchDao.list();
		}
		Gson gson = new Gson();
		return gson.toJson(branches);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/colleges", method=RequestMethod.GET)
	public String getColleges(){
		Gson gson = new Gson();
		return gson.toJson(collegeDao.list());
	}
	
	@ResponseBody
	@RequestMapping(value="collegeMapJson", method=RequestMethod.GET)
    public String getData() throws IOException {
		Map<Object, Object> subjectMap, branchObj, branchMap, collegeMap, collegeObj;
		
		collegeMap = new HashMap<Object, Object>();
		
		for (College college: collegeDao.list()) {
			branchMap = new HashMap<Object, Object>();
			for (Branch branch: branchDao.list(college)) {
				subjectMap = new HashMap<Object, Object>();
				for (Subject subject: subjectDao.list(branch)) {
					subjectMap.put(subject.getName(), subject.getSubjectID());
				}
				branchObj = new HashMap<Object, Object>();
				branchObj.put("branchID", branch.getBranchID());
				branchObj.put("subjects", subjectMap);
				branchMap.put(branch.getName(), branchObj);
			}
			collegeObj = new HashMap<Object, Object>();
			collegeObj.put("collegeID", college.getCollegeID());
			collegeObj.put("branches", branchMap);
			collegeMap.put(college.getName(), collegeObj);
		}
		
		Gson gson = new Gson();
		return gson.toJson(collegeMap);
    }
	
}
