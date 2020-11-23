/**
 * 
 */
package com.example.CarLook_Ltd.factories;



import com.example.CarLook_Ltd.dto.BookingRequest;
import com.example.CarLook_Ltd.dto.User;
import com.example.CarLook_Ltd.entities.Booking;

/**
 * @author kpierz2s
 *
 */
public class BookingFactory {

	
	public static Booking createBooking(BookingRequest request, User user) {
		Booking book = new Booking();
		
		
		book.setAuto(request.getAuto());

		
		book.setUser(user);
		
		
		return book;
	}

}
