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
@Table(name = "Branch")
public class Branch {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branchID")
	private int branchID;
	
    @NotNull
    @Column(name = "name")
	private String name;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "collegeID")
    private College college;
	
	public Branch() {}
	
	public Branch(String name) {
		this.name = name;
	}

	public Branch(String name, College college) {
		this.name = name;
		this.college = college;
	}

	public int getBranchID() {
		return branchID;
	}

	public void setBranchID(int branchID) {
		this.branchID = branchID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

}
