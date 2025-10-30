package de.sopro.sbs_app.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

/**
 * Projects. Associated with one address, contracts.
 */
@Entity(tableName = "projects")
public class Project {

	// ------ Attributes ------//
	@PrimaryKey
	private Long id;
	private String name;
	private String description;
	private String creationDate;
	private String completionDate;
	private Double overallCosts;
	private String creator;
	private String organisation;

	// ------ Associations ------//
	@Embedded
	private Status status;
	@Embedded
	private Address address;
	@Ignore
	private List<Contract> contracts;

	// ----------------------------------//
	// ---------- Constructors ----------//
	// ----------------------------------//
	public Project(Long id, String name, String description, String creationDate,
				   String completionDate, Double overallCosts, String creator, Address address,
				   String organisation, Status status) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.creationDate = creationDate;
		this.completionDate = completionDate;
		this.overallCosts = overallCosts;
		this.creator = creator;
		this.address = address;
		this.organisation = organisation;
		this.status = status;
	}

	// ----------------------------//
	// ---------- Getter ----------//
	// ----------------------------//
	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public String getCreationDate() {
		return this.creationDate;
	}

	public String getCompletionDate() {
		return this.completionDate;
	}

	public Double getOverallCosts() {
		return this.overallCosts;
	}

	public String getCreator() {
		return this.creator;
	}

	public Address getAddress() { return this.address; }

	public List<Contract> getContracts() {
		return this.contracts;
	}

	public String getOrganisation() { return organisation; }

	public Status getStatus() { return status; }

	// ----------------------------//
	// ---------- Setter ----------//
	// ----------------------------//
	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String n) {
		this.name = n;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}

	public void setCreator(String creator) { this.creator = creator; }

	public void setOverallCosts(Double overallCosts) {
		this.overallCosts = overallCosts;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}

	public void setOrganisation(String organisation) { this.organisation = organisation; }

	public void setStatus(Status status) { this.status = status; }

}
