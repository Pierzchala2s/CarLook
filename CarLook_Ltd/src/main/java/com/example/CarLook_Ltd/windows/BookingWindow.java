/**
 * 
 */
package com.example.CarLook_Ltd.windows;



import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;

import com.vaadin.ui.Label;

import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import com.example.CarLook_Ltd.control.BookingProcess;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import com.example.CarLook_Ltd.dto.BookingRequest;
import com.example.CarLook_Ltd.dto.Auto;

/**
 * @author kpierz2s
 *
 */
public class BookingWindow extends Window {
	
	public BookingWindow ( final Auto auto) {
		super("Buchung"); //set window caption
		center();
		
		//some basic content for the window
		VerticalLayout content = new VerticalLayout();
		content.addComponent(new Label ("Buchung für Auto: ID " + auto.getId() + " Marke : "+auto.getMarke() + " BauJahr: " +auto.getBaujahr() + " Beschreibung: "+ auto.getDescription()));
		content.setMargin(true);
		setContent(content);
	
		//Enable the close button
		
		setClosable(true);
		
		// Implementierung Button
		Button buchungsButton = new Button("Buche");
		buchungsButton.addClickListener(new ClickListener() {
			
			public void buttonClick(ClickEvent event) {
			
				BookingRequest request = new BookingRequest();
				request.setAuto(auto);
				
				BookingProcess.getInstance().createBooking(request, BookingWindow.this);
		
			}
		});
		
		content.addComponent(buchungsButton);
		content.setComponentAlignment(buchungsButton, Alignment.MIDDLE_CENTER);
		
	}

		
	
}
