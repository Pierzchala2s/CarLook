/**
 * 
 */
package com.example.CarLook_Ltd.control;


import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import java.util.List;
import com.example.CarLook_Ltd.UI.MyUI;
import com.example.CarLook_Ltd.dao.BuchungDAO;
import com.example.CarLook_Ltd.dto.BookingDetail;
import com.example.CarLook_Ltd.dto.BookingRequest;
import com.example.CarLook_Ltd.dto.User;
import com.example.CarLook_Ltd.entities.Booking;
import com.example.CarLook_Ltd.factories.BookingFactory;
import com.example.CarLook_Ltd.windows.ConfirmationWindow;


/**
 * @author kpierz2s
 *
 */
public class BookingProcess {
	
	public static BookingProcess process = null;
	
	private BookingProcess() {
		
	}
	
	public static BookingProcess getInstance() {
		if( process == null) {
			process = new BookingProcess();
		}
		return process;
		
	}
	
	public void createBooking(BookingRequest request, Window window) {
		//User user = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
		User user = ((MyUI) UI.getCurrent()).getUser();
		Booking booking = BookingFactory.createBooking(request, user);
		
		boolean result = BuchungDAO.getInstance().addBooking(booking);
		//Navigation auf Basis der un/erfolgreichen Buchung
		if ( result == true) {
			window.close();
			UI.getCurrent().addWindow(new ConfirmationWindow("Buchung erfolgreich! Auto ID: " + booking.getAuto().getId()));
			
		} else {
			window.close();
			UI.getCurrent().addWindow(new ConfirmationWindow("Buchung fehlgeschlagen Auto ID: " + booking.getAuto().getId()));
		}
		
	}

	/**
	 * @return
	 */
	public List<BookingDetail> getAllBookingForUser() {
		User user = ((MyUI) UI.getCurrent()).getUser();
		System.out.println(user);
		
		return BuchungDAO.getInstance().getAllBookingsForUser(user);
		
	}
	

}
