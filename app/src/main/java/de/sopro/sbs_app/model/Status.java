package de.sopro.sbs_app.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Status for billing items. Standard status: NO_STATUS, OPEN, OK, DENY
 */
@Entity(tableName = "status",
        foreignKeys = {
            @ForeignKey(
                    entity = Status.class,
                    parentColumns = "status",
                    childColumns = "status_FK"
            )})
public class Status {
    // ---- Attributes ----//
    @PrimaryKey
    @NotNull
    private String status;
    private String statusDescription;

    // ---- Foreign keys ----//
    @ColumnInfo(name = "status_FK")
    private String status_FK;

    // ---- Associations ----//
    @Ignore
    private List<Status> successors;

    // ----------------------------------//
    // ---------- Constructors ----------//
    // ----------------------------------//
    public Status(String status, String statusDescription, String status_FK) {
        this.status = status;
        this.statusDescription = statusDescription;
        this.status_FK = status_FK;
    }

    // ----------------------------//
    // ---------- Getter ----------//
    // ----------------------------//
    public String getStatus() { return status; }

    public String getStatusDescription() { return statusDescription; }

    public String getStatus_FK() { return status_FK; }

    public List<Status> getSuccessors() { return successors; }

    // ----------------------------//
    // ---------- Setter ----------//
    // ----------------------------//
    public void setStatus(String status) { this.status = status; }

    public void setStatusDescription(String statusDescription) { this.statusDescription = statusDescription; }

    public void setStatus_FK(String status_FK) { this.status_FK = status_FK; }

    public void setSuccessors(List<Status> successors) { this.successors = successors; }
}
