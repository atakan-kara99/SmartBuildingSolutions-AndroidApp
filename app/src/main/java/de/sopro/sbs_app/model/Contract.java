package de.sopro.sbs_app.model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

/**
 * Contract of each organization. Each contract is associated to one or more
 * billing units, one project.
 */
@Entity(tableName = "contracts",
		foreignKeys = {
			@ForeignKey(
					entity = Project.class,
					parentColumns = "id",
					childColumns = "project_FK"
			)})
public class Contract {
	// ---- Attributes ----//
	@PrimaryKey
	private Long id;
	private String name;
	private String description;
	private String consignee;
	private String contractor;

	// ---- Foreign keys ----//
	@ColumnInfo(name = "project_FK", index = true)
	private Long project_FK;

	// ---- Associations ----//
	@Embedded
	private Status status;
	@Ignore
	private List<BillingUnit> billingUnits;

	// ----------------------------------//
	// ---------- Constructors ----------//
	// ----------------------------------//
	public Contract(Long id, String name, String description, String consignee,
					String contractor, Long project_FK, Status status) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.consignee = consignee;
		this.contractor = contractor;
		this.project_FK = project_FK;
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

	public String getConsignee() {
		return this.consignee;
	}

	public String getContractor() {
		return this.contractor;
	}

	public List<BillingUnit> getBillingUnits() {
		return this.billingUnits;
	}

	public Long getProject_FK() { return project_FK; }

	public Status getStatus() { return status; }

	// ----------------------------//
	// ---------- Setter ----------//
	// ----------------------------//
	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public void setContractor(String contractor) {
		this.contractor = contractor;
	}

	public void setBillingUnit(List<BillingUnit> billingUnits) {
		this.billingUnits = billingUnits;
	}

	public void setProject_FK(Long project_FK) { this.project_FK = project_FK; }

	public void setBillingUnits(List<BillingUnit> billingUnits) {
		this.billingUnits = billingUnits;
	}

	public void setStatus(Status status) { this.status = status; }

}
