package papersdb.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Paper")
public class Paper implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "paperID")
	private int paperID;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "subjectID")
	private Subject subject;

	@Column(name = "path")
	private String path;

	@Column(name = "description")
	private String description;
	
	@Column(name = "month")
	private String month;
	
	@Column(name = "year")
	private Integer year;

	@Column(name = "counter")
	private int counter = 0;

	public Paper() { }

	public Paper(Subject subject) {
		this.subject = subject;
	}

	public Paper(Subject subject, String description) {
		this.subject = subject;
		this.description = description;
	}
	public Paper(Subject subject, String description, Integer year) {
		this(subject, description);
		this.year = year;
	}

	public Paper(Subject subject, String description, Integer year, String month) {
		this(subject, description, year);
		this.month = month;
	}

	public int getPaperID() {
		return paperID;
	}

	public void setPaperID(int paperID) {
		this.paperID = paperID;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public void increaseCounter() {
		counter++;
	}

}
