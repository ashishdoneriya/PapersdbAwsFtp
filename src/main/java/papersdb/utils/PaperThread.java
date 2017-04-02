package papersdb.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import papersdb.beans.ServerStorage;
import papersdb.dao.BranchDao;
import papersdb.dao.CollegeDao;
import papersdb.dao.PaperDao;
import papersdb.dao.SubjectDao;
import papersdb.model.Branch;
import papersdb.model.College;
import papersdb.model.Paper;
import papersdb.model.Subject;

public class PaperThread extends Thread {

	private static final Logger LOG = Logger.getLogger(PaperThread.class);

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

	@Override
	public void run() {
		File file = new File(serverStorage.getServerPapersUploadDir());
		if (file.exists() && file.isDirectory()) {
			addPapers(file);
		}
	}

	private void addPapers(File root) {
		for (File collegeDir : root.listFiles()) {
			for (File branchDir : root.listFiles()) {
				for (File subjectDir : branchDir.listFiles()) {
					for (File file : subjectDir.listFiles()) {
						String[] str = file.getName().split("\\|");
						String collegeName = collegeDir.getName();
						String branchName = branchDir.getName();
						String subjectName = subjectDir.getName();
						String month = str[1];
						String sYear = str[2];
						String description = str[3];
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
						String fileName = createFileName(paper) + getExtension(file.getName());
						try {
							serverStorage.uploadFile(serverStorage.getPapersDir() + fileName,
									new FileInputStream(file));
							paper.setPath(fileName);
							paperDao.update(paper);
						} catch (IOException e) {
							LOG.error(e);
						}
						root.delete();
					}
				}
				
			}
		}

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
