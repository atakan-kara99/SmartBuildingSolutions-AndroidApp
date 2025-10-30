package de.sopro.sbs_app.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

/**
 * User class with association to organisations.
 */
@Entity(tableName = "users")
public class User {
	// ---- Attributes ----//
	@PrimaryKey
	private Long id;
	private String forename;
	private String lastname;
	private String username;
	private String password;
	private String role;
	private String organisation;

	// ----------------------------------//
	// ---------- Constructors ----------//
	// ----------------------------------//
	public User(Long id, String forename, String lastname, String username, String password,
				String role, String organisation) {
		this.id = id;
		this.forename = forename;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.role = role;
		this.organisation = organisation;
	}

	// ----------------------------//
	// ---------- Getter ----------//
	// ----------------------------//
	public Long getId() {
		return this.id;
	}

	public String getForename() {
		return this.forename;
	}

	public String getLastname() {
		return this.lastname;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() { return password; }

	public String getRole() {
		return this.role;
	}

	public String getOrganisation() { return organisation; }

	// ----------------------------//
	// ---------- Setter ----------//
	// ----------------------------//
	public void setId(Long id) {
		this.id = id;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) { this.password = password; }

	public void setOrganisation(String organisation) { this.organisation = organisation; }

	public void setRole(String role) { this.role = role; }

}