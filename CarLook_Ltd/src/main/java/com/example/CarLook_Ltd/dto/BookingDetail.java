/**
 * 
 */
package com.example.CarLook_Ltd.dto;

/**
 * @author kpierz2s
 *
 */
public class BookingDetail {
	

	private String auto;
	private User user;
	private int id;
	private int bookingid;

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getAuto() {
		return auto;
	}

	public void setAuto(String auto) {
		this.auto = auto;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public int getBookingid() {
		return bookingid;
	}

	public void setBookingid(int bookingid) {
		this.bookingid = bookingid;
	}
	
	
}
