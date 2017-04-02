package papersdb.model;

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
@Table(name = "Subject")
public class Subject {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subjectID")
	private int subjectID;
	
    @NotNull
    @Column(name = "name")
	private String name;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "branchID")
    private Branch branch;
	
	public Subject() { }
	
	public Subject(String name) {
		this.name = name;
	}
	
	public Subject(String name, Branch branch) {
		this.name = name;
		this.branch = branch;
	}
	
	public int getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
}
