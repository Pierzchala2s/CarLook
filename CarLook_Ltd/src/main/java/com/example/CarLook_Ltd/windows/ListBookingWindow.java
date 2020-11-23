/**
 * 
 */
package com.example.CarLook_Ltd.windows;

import java.util.List;


import com.example.CarLook_Ltd.control.BookingProcess;
import com.example.CarLook_Ltd.dto.BookingDetail;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * @author kpierz2s
 *
 */
public class ListBookingWindow extends Window{
	
	private int currentID;
	private List<BookingDetail> liste;
	
	public ListBookingWindow() {
		super("Liste aller Buchungen");
		center();
		VerticalLayout layout = new VerticalLayout();
	    liste = BookingProcess.getInstance().getAllBookingForUser();
		Grid<BookingDetail> grid = new Grid<>();
	
		// Columns definieren
		
	    grid.addColumn(BookingDetail::getAuto).setCaption("User");
	    grid.addColumn(BookingDetail::getId).setCaption("Auto ID");
	    grid.addColumn(BookingDetail::getBookingid).setCaption("Booking ID");
	    
	    // Daten einsetzen
	    grid.setItems(liste);
	    
	    // Tabelle darstellen
	    layout.addComponent(grid);
	    setContent(layout);
     
	}

}
