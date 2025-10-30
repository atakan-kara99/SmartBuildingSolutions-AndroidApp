package de.sopro.sbs_app.model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

/**
 * Billing Item of a project. Associated to one billing unit
 * and to itself and to many construction progresses.
 */
@Entity(tableName = "billingitems",
		foreignKeys = {
			@ForeignKey(
					entity = BillingItem.class,
					parentColumns = "id",
					childColumns = "billingItem_FK"
			),
			@ForeignKey(
					entity = BillingUnit.class,
					parentColumns = "id",
					childColumns = "billingUnit_FK"
			)})
public class BillingItem {
	// ---- Attributes ----//
	@PrimaryKey
	private Long id;
	private double price;
	private String shortDescription;
	private double quantities;
	private String unit;
	private double unitPrice;
	private String qtySplit;

	// ---- Foreign keys----//
	@ColumnInfo(name = "billingItem_FK", index = true)
	private Long billingItem_FK;
	@ColumnInfo(name = "billingUnit_FK", index = true)
	private Long billingUnit_FK;

	// ---- Associations ----//
	@Embedded
	private Status status;
	@Ignore
	private List<BillingItem> billingItems;
	@Ignore
	private List<ConstructionProgress> constructionProgress;

	// ----------------------------------//
	// ---------- Constructors ----------//
	// ----------------------------------//
	public BillingItem(Long id, double price, String shortDescription, Status status,
					   double quantities, String unit, double unitPrice, String qtySplit,
					   Long billingItem_FK, Long billingUnit_FK) {
		this.id = id;
		this.price = price;
		this.shortDescription = shortDescription;
		this.status = status;
		this.quantities = quantities;
		this.unit = unit;
		this.unitPrice = unitPrice;
		this.qtySplit = qtySplit;
		this.billingItem_FK = billingItem_FK;
		this.billingUnit_FK = billingUnit_FK;
	}

	// ----------------------------//
	// ---------- Getter ----------//
	// ----------------------------//
	public Long getId() {
		return this.id;
	}

	public double getPrice() {
		return this.price;
	}

	public String getShortDescription() {
		return this.shortDescription;
	}

	public Status getStatus() {
		return this.status;
	}

	public double getQuantities() {
		return this.quantities;
	}

	public String getUnit() {
		return this.unit;
	}

	public double getUnitPrice() {
		return this.unitPrice;
	}

	public String getQtySplit() {
		return this.qtySplit;
	}

	public List<BillingItem> getBillingItems() {
		return this.billingItems;
	}

	public List<ConstructionProgress> getConstructionProgress() {
		return constructionProgress;
	}

	public Long getBillingItem_FK() { return billingItem_FK; }

	public Long getBillingUnit_FK() { return billingUnit_FK; }

	// ----------------------------//
	// ---------- Setter ----------//
	// ----------------------------//
	public void setId(Long id) {
		this.id = id;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setQuantities(double quantities) {
		this.quantities = quantities;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public void setQtySplit(String qtySplit) {
		this.qtySplit = qtySplit;
	}

	public void setBillingItems(List<BillingItem> billingItems) {
		this.billingItems = billingItems;
	}

	public void setConstructionProgress(List<ConstructionProgress> constructionProgress) {
		this.constructionProgress = constructionProgress;
	}

	public void setBillingItem_FK(Long billingItem_FK) {
		this.billingItem_FK = billingItem_FK;
	}

	public void setBillingUnit_FK(Long billingUnit_FK) {
		this.billingUnit_FK = billingUnit_FK;
	}

}
