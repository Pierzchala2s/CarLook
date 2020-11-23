package com.example.CarLook_Ltd.dto;


/**
 * @author kpierz2s
 *
 */
@SuppressWarnings("serial")
public class Auto implements java.io.Serializable{

	
	private String marke;
	private Integer id;
	private String baujahr;
	private String description;

	public Auto(int id, String marke, String baujahr, String description) {
		
		this.marke = marke;
		this.id = id;
		this.baujahr = baujahr;
		this.description = description;
	}
	

	public Auto () {
		
	}


	public String getMarke() {
		return marke;
	}


	public void setMarke(String marke) {
		this.marke = marke;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getBaujahr() {
		return baujahr;
	}


	public void setBaujahr(String baujahr) {
		this.baujahr = baujahr;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

}
