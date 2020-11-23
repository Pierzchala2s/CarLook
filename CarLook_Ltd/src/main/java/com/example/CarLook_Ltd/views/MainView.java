/**
 * 
 */
package com.example.CarLook_Ltd.views;

import com.vaadin.ui.*;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.grid.HeightMode;
import java.sql.SQLException;
import java.util.List;
import com.example.CarLook_Ltd.Exceptions.DatabaseException;
import com.example.CarLook_Ltd.UI.MyUI;
import com.example.CarLook_Ltd.components.NavigationBar;
import com.example.CarLook_Ltd.components.TopPanel;
import com.example.CarLook_Ltd.control.AutoSearch;
import com.example.CarLook_Ltd.dao.AutoDAO;
import com.example.CarLook_Ltd.dto.Auto;
import com.example.CarLook_Ltd.dto.User;
import com.example.CarLook_Ltd.util.Views;
import com.example.CarLook_Ltd.windows.BookingWindow;

/**
 * @author kpierz2s
 *
 */

	public class MainView extends VerticalLayout implements View {
		
	 private int anzahl = 0;
	 private Auto selected = null;

		@Override
		public void enter(ViewChangeEvent event) {
			
			//User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);
			User user = ((MyUI) UI.getCurrent()).getUser();
			if ( user == null) {  // <---------------------------------------------- == setzen
				UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
			} else {
				this.setUp();
			}
		
			}
		
	
	
		public void setUp() {
			
			this.addComponent(new TopPanel());
			NavigationBar navigationBar = new NavigationBar();
			navigationBar.NavigationBarWithLogout();
	        this.addComponent(navigationBar);
	        this.setComponentAlignment(navigationBar, Alignment.TOP_LEFT);
	      
			
	        final VerticalLayout layout = new VerticalLayout();        
	        final HorizontalLayout horizon = new HorizontalLayout();
	 
	        final Label label = new Label("Gebe eine AutoMarke ein:");
	        Button button = new Button("Suche", FontAwesome.SEARCH);
	        button.setClickShortcut(ShortcutAction.KeyCode.ENTER);
	        Button buchen = new Button("Buchen", FontAwesome.BOOK);
	       
	        
	        final TextField textinput = new TextField();
	       
	        horizon.addComponents(label, textinput, button);
	        layout.addComponent(horizon);
	 
	        layout.setComponentAlignment(horizon, Alignment.MIDDLE_CENTER);
	        horizon.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
	 
	        
	        Panel panel = new Panel("Suchfenster");
			panel.addStyleName("search");
			
			this.addComponent(panel);
			this.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
			
	        
	        Grid<Auto> grid = new Grid<>();
	        grid.setSizeFull();
	        grid.setHeightMode(HeightMode.UNDEFINED);
	 
	        // Die aktuell angewählte Zeile in der Tabelle (aka dem Grid)
	        SingleSelect<Auto> selection = grid.asSingleSelect();
	 
	        // Der Event Listener für den Grid
	        grid.addSelectionListener(event -> {
	   
	            // Speichert den aktuell angewählten Wert bei klick auf die Zeile in der Var. "selected"
	          this.selected = selection.getValue();
	          System.out.println( "Auswahl: " + this.selected.toString());
	          
	    });
	 
	        // Event Listener für den Suchen Button
	        button.addClickListener(e -> {
	           
	            String marke = textinput.getValue();
	            List<Auto> liste = AutoSearch.getInstance().getAutoByMarke(marke);
	 
	            if (marke.equals("")) {
	                Notification.show(null, "Bitte Automarke eingeben!", Notification.Type.WARNING_MESSAGE);
	            }
	            anzahl += 1;
	 
	            //erstmal alles löschen
	            grid.removeAllColumns();
	            grid.setCaption("Treffer für " + marke + " (Anzahl der Suchen: " + anzahl + ")");
	           
	            // neue Items hinzufügen
	            grid.setItems(liste);
	 
	            // Columns definieren
	            grid.addColumn(Auto::getMarke).setCaption("Marke");
	            grid.addColumn(Auto::getId).setCaption("ID");
	            grid.addColumn(Auto::getBaujahr).setCaption("Baujahr");
	            grid.addColumn(Auto::getDescription).setCaption("Beschreibung");
	        });
	        
	        
	        List<Auto> list = null;
	        try {
				list = new AutoDAO().retrieveAutos("");
			} catch (DatabaseException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        
        grid.setItems(list);
      
        grid.addColumn(Auto::getMarke).setCaption("Marke");
        grid.addColumn(Auto::getId).setCaption("ID");
        grid.addColumn(Auto::getBaujahr).setCaption("Baujahr");
        grid.addColumn(Auto::getDescription).setCaption("Beschreibung");
	        
        
        // ON THE FLY Search
        
	        textinput.addValueChangeListener(d -> {
	            if (!textinput.getValue().equals("")) {
	            	String attribute = textinput.getValue();
	            	
	                grid.removeAllColumns();
	                List<Auto> liste2 = null;
	                try {
	                liste2 = new AutoDAO().retrieveAutos(attribute);
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	                grid.setItems(liste2);
	              
	                grid.addColumn(Auto::getMarke).setCaption("Marke");
		            grid.addColumn(Auto::getId).setCaption("ID");
		            grid.addColumn(Auto::getBaujahr).setCaption("Baujahr");
		            grid.addColumn(Auto::getDescription).setCaption("Beschreibung");
	                //addComponent(new Label(" erfolgreiche Eingabe! Suche wird gestartet"));
	            } else {
	            	String attribute = textinput.getValue();
	                grid.removeAllColumns();
	                List<Auto> liste3 = null;
	                try {
	                    liste3 = new AutoDAO().retrieveAutos(attribute);
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	                grid.setItems(liste3);
	               
	                grid.addColumn(Auto::getMarke).setCaption("Marke");
		            grid.addColumn(Auto::getId).setCaption("ID");
		            grid.addColumn(Auto::getBaujahr).setCaption("Baujahr");
		            grid.addColumn(Auto::getDescription).setCaption("Beschreibung");
	            }
	        });
	        
	        
	 
	        buchen.addClickListener(e -> {
	            // Testweise Ausgabe der derzeitigen Selektion in einer Notification
	            
	            BookingWindow window = new BookingWindow(MainView.this.selected);
	            UI.getCurrent().addWindow(window);
	        });
	 
	        // Grid und Buchen Button richtig anordnen
	        layout.addComponent(grid);
	        
	        layout.addComponent(buchen);
	        layout.setComponentAlignment(buchen, Alignment.MIDDLE_CENTER);
	       
			panel.setContent(layout);
			
		}

	}
		    
	      


