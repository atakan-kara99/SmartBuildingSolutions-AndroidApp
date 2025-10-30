package de.sopro.sbs_app.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * Construction progress. Associated with one billing item.
 */
@Entity(tableName = "constructionprogress",
        foreignKeys = {
            @ForeignKey(
                entity = BillingItem.class,
                parentColumns = "id",
                childColumns = "billingItem_FK"
            )})
public class ConstructionProgress {
    // ---- Attributes ----//
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String img;
    private String description;

    // ---- Foreign keys----//
    @ColumnInfo(name = "billingItem_FK", index = true)
    private Long billingItem_FK;

    // ----------------------------------//
    // ---------- Constructors ----------//
    // ----------------------------------//
    public ConstructionProgress(String img, String description, Long billingItem_FK) {
        this.img = img;
        this.description = description;
        this.billingItem_FK = billingItem_FK;
    }

    // ----------------------------//
    // ---------- Getter ----------//
    // ----------------------------//
    public Long getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public String getDescription() {
        return description;
    }

    public Long getBillingItem_FK() { return billingItem_FK; }

    // ----------------------------//
    // ---------- Setter ----------//
    // ----------------------------//
    public void setId(Long id) {
        this.id = id;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBillingItem_FK(Long billingItem_FK) { this.billingItem_FK = billingItem_FK; }

}
