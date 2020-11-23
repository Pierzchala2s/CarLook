/**
 * 
 */
package com.example.CarLook_Ltd.components;



import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;
import com.example.CarLook_Ltd.UI.*;
import com.example.CarLook_Ltd.control.LoginControl;
import com.example.CarLook_Ltd.dto.User;
import com.example.CarLook_Ltd.windows.ListBookingWindow;



/**
 * @author kpierz2s
 *
 */
public class TopPanel extends HorizontalLayout {
	
	public TopPanel () {
		
		this.setSizeFull();
		
		Label headLabel = new Label("CarLook - <i>das Autosuchsystem</i>", ContentMode.HTML);
		headLabel.setSizeUndefined();
		
		
		this.addComponent(headLabel);
		this.setComponentAlignment(headLabel, Alignment.TOP_LEFT);
		
		HorizontalLayout horLayout = new HorizontalLayout();
		
		//User user = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
		User user = ((MyUI) UI.getCurrent()).getUser();
		
		
		String vorname = null;
		if(user != null) {
			vorname = user.getVorname();
		}
		
		
		Label loggedLabel = new Label ("Willkommen: "+ vorname+"!");
		loggedLabel.setSizeUndefined();
		//loggedLabel.addStyleName("loggedLabel");
		
		horLayout.addComponent(loggedLabel);
		horLayout.setComponentAlignment(loggedLabel, Alignment.MIDDLE_CENTER);
		
		MenuBar bar = new MenuBar();
		MenuItem item1 = bar.addItem("Menu", null);
		
		//Logout des user 
		
		item1.addItem("Logout", FontAwesome.SIGN_OUT, new MenuBar.Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				
				LoginControl.logoutUser();
				
			}
		});
		
		item1.addItem("Buchungen", FontAwesome.SIGN_OUT, new MenuBar.Command() {
			
			@Override
			public void menuSelected(MenuItem selectedItem) {
				
				ListBookingWindow window = new ListBookingWindow();
				UI.getCurrent().addWindow(window);
				
			}
		});
		
		horLayout.addComponent(bar);
		this.addComponent(horLayout);
		this.setComponentAlignment(horLayout, Alignment.TOP_RIGHT);
		
	 }
	}


