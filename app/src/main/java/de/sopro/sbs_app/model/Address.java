package de.sopro.sbs_app.model;

/**
 * Address of each project.
 */
public class Address {
	// ---- Attributes ----//
	private Long addressID;
	private String street;
	private int houseNumber;
	private int zipCode;
	private String city;
	private String country;

	// ----------------------------------//
	// ---------- Constructors ----------//
	// ----------------------------------//
	public Address(Long addressID, String street, int houseNumber, int zipCode, String city,
				   String country) {
		this.addressID = addressID;
		this.street = street;
		this.houseNumber = houseNumber;
		this.zipCode = zipCode;
		this.city = city;
		this.country = country;
	}

	// ----------------------------//
	// ---------- Getter ----------//
	// ----------------------------//
	public Long getAddressID() {
		return this.addressID;
	}

	public String getStreet() {
		return this.street;
	}

	public int getHouseNumber() {
		return this.houseNumber;
	}

	public int getZipCode() {
		return this.zipCode;
	}

	public String getCity() {
		return this.city;
	}

	public String getCountry() {
		return this.country;
	}

	// ----------------------------//
	// ---------- Setter ----------//
	// ----------------------------//
	public void setAddressID(Long addressID) { this.addressID = addressID; }

	public void setStreet(String s) {
		this.street = s;
	}

	public void setHouseNumber(int hNum) {
		this.houseNumber = hNum;
	}

	public void setZipCode(int zCd) {
		this.zipCode = zCd;
	}

	public void setCity(String c) {
		this.city = c;
	}

	public void setCountry(String cntry) {
		this.country = cntry;
	}

	@Override
	public String toString() {
		return street + " " +
				houseNumber + " \n" +
				zipCode + " " +
				city + " \n" +
				country;
	}
}
