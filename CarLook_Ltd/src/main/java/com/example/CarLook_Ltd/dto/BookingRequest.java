/**
 * 
 */
package com.example.CarLook_Ltd.dto;

import java.util.Date;

/**
 * @author kpierz2s
 *
 */
public class BookingRequest {
	
	private Date anreise = null;
	private Date abreise = null;
	private String iban = null;
	private int number;
	private Auto auto;
	
	
	public Date getAnreise() {
		return anreise;
	}
	public void setAnreise(Date anreise) {
		this.anreise = anreise;
	}
	public Date getAbreise() {
		return abreise;
	}
	public void setAbreise(Date abreise) {
		this.abreise = abreise;
	}
	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Auto getAuto() {
		return auto;
	}
	public void setAuto(Auto auto) {
		this.auto = auto;
	}
	
	
	

}
