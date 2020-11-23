/**
 * 
 */
package com.example.CarLook_Ltd.entities;



import com.example.CarLook_Ltd.dto.Auto;
import com.example.CarLook_Ltd.dto.User;

/**
 * @author kpierz2s
 *
 */
public class Booking {
	private int id ;
	private int number;
	private Auto auto;
	private User user;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
