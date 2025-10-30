package de.sopro.sbs_app.model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

/**
 * BillingUnit of each contract. Associated to one contract and one or more
 * billing items.
 */
@Entity(tableName = "billingunits",
		foreignKeys = {
			@ForeignKey(
					entity = Contract.class,
					parentColumns = "id",
					childColumns = "contract_FK"
			)})
public class BillingUnit {
	// ---- Attributes ----//
	@PrimaryKey
	private Long id;
	private String shortDescription;
	private String longDescription;
	private String unit;
	private String completionDate;
	private double totalQuantity;
	private double totalPrice;

	// ---- Foreign keys ----//
	@ColumnInfo(name = "contract_FK", index = true)
	private Long contract_FK;

	// ---- Associations ----//
	@Embedded
	private Status status;
	@Ignore
	private List<BillingItem> billingItems;

	// ----------------------------------//
	// ---------- Constructors ----------//
	// ----------------------------------//
	public BillingUnit(Long id, String shortDescription, String longDescription, String unit,
					   String completionDate, double totalQuantity, double totalPrice,
					   Long contract_FK, Status status) {
		this.id = id;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.unit = unit;
		this.completionDate = completionDate;
		this.totalQuantity = totalQuantity;
		this.totalPrice = totalPrice;
		this.contract_FK = contract_FK;
		this.status = status;
	}

	// ----------------------------//
	// ---------- Getter ----------//
	// ----------------------------//
	public Long getId() {
		return this.id;
	}

	public String getShortDescription() {
		return this.shortDescription;
	}

	public String getLongDescription() {
		return this.longDescription;
	}

	public String getUnit() {
		return this.unit;
	}

	public String getCompletionDate() {
		return this.completionDate;
	}

	public double getTotalQuantity() {
		return this.totalQuantity;
	}

	public double getTotalPrice() {
		return this.totalPrice;
	}

	public List<BillingItem> getBillingItems() {
		return this.billingItems;
	}

	public Long getContract_FK() { return contract_FK; }

	public Status getStatus() { return status; }

	// ----------------------------//
	// ---------- Setter ----------//
	// ----------------------------//
	public void setId(Long id) {
		this.id = id;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}

	public void setTotalQuantity(double totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setBillingItems(List<BillingItem> billingItems) {
		this.billingItems = billingItems;
	}

	public void setContract_FK(Long contract_FK) { this.contract_FK = contract_FK; }

	public void setStatus(Status status) { this.status = status; }

}
