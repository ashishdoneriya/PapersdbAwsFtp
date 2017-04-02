package papersdb.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PaperSubmitRequest")
public class PaperSubmitRequest implements Serializable {
	
	public PaperSubmitRequest() { }
	
	public PaperSubmitRequest(String college, String branch,
			String subject, String description) {
		this.college = college;
		this.branch = branch;
		this.subject = subject;
		this.description = description;
	}

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "requestID")
	private int requestID;
	
    @Column(name = "college")
    private String college;
	
    @Column(name = "branch")
    private String branch;
	
    @Column(name = "subject")
    private String subject;
    
    @Column(name = "description")
    private String description;

	public int getRequestID() {
		return requestID;
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
