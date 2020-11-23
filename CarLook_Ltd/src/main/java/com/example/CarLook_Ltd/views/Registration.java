/**
 * 
 */
package com.example.CarLook_Ltd.views;



import java.util.ArrayList;
import java.util.List;

import com.example.CarLook_Ltd.UI.MyUI;
import com.example.CarLook_Ltd.components.NavigationBar;
import com.example.CarLook_Ltd.dao.UserDAO;
import com.example.CarLook_Ltd.dto.User;
import com.example.CarLook_Ltd.util.PasswortValidator;
import com.example.CarLook_Ltd.util.Views;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import java.sql.*;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * @author kpierz2s
 *
 */
public class Registration extends VerticalLayout implements View {
	
	
	
    private final Panel userCreationPanel = new Panel("Schritt 1: Geben Sie Ihre Daten ein");
    
    private final Binder<User> userBinder = new Binder<>();
	
	public void enter(ViewChangeEvent event) {
		
		//User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);
		User user = ((MyUI) UI.getCurrent()).getUser();
		if ( user != null) {  
			UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
		} else {
			try {
				this.setUp();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		}
	


	public void setUp() throws SQLException {
		
		NavigationBar navigationBar = new NavigationBar();
		
        this.addComponent(navigationBar);
        this.setComponentAlignment(navigationBar, Alignment.TOP_LEFT);
        
		userCreationPanel.setVisible(true);
        this.setSizeUndefined();
        this.addComponent(userCreationPanel);
        this.setComponentAlignment(userCreationPanel, Alignment.TOP_CENTER);
        
        Button weiterButton1 = new Button("Registrieren", VaadinIcons.ARROW_RIGHT);
        weiterButton1.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        Button backButton1 = new Button("Zurück", VaadinIcons.ARROW_RIGHT);
        FormLayout content = new FormLayout();
        content.setSizeUndefined();
        
        TextField emailField = new TextField("E-Mail Adresse");
        userBinder.forField(emailField).asRequired(new EmailValidator("Bitte geben Sie eine gültige E-Mail Adresse an"))
                .bind(User::getEmail, User::setEmail);

        PasswordField passwordField = new PasswordField("Passwort");
        userBinder.forField(passwordField).asRequired(new PasswortValidator())
                .bind(User::getPassword, User::setPassword);
        
        PasswordField passwordCheckField = new PasswordField("Passwort wiederholen");
        
        TextField vorname = new TextField("Vorname:");
        vorname.setRequiredIndicatorVisible(true);
        userBinder.forField(vorname).asRequired("Bitte geben Sie Ihren Vornamen an")
                .bind(User::getVorname, User::setVorname);

        TextField nachname = new TextField("Nachname");
        nachname.setRequiredIndicatorVisible(true);
        userBinder.forField(nachname).asRequired("Bitte geben Sie Ihren Nachnamen an")
                .bind(User::getNachname, User::setNachname);
        
        content.addComponents(emailField, passwordField, passwordCheckField,vorname,nachname,backButton1, weiterButton1);
        content.setSizeUndefined();

        content.setMargin(true);
        userCreationPanel.setContent(content);

        this.setComponentAlignment(userCreationPanel, Alignment.TOP_CENTER);
        
        backButton1.addClickListener(clickEvent -> {
        	 UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
        });
        
        weiterButton1.addClickListener(clickEvent -> {
            if (!passwordField.getValue().equals(passwordCheckField.getValue())) {
                Notification notification = new Notification("Die Passwörter stimmen nicht überein.", Notification.Type.ERROR_MESSAGE);
                notification.setPosition(Position.BOTTOM_CENTER);
                notification.setDelayMsec(4000);
                notification.show(Page.getCurrent()); }
                //return;
                
              else  { 
               
                    User myUser = new User();
                	 boolean isValidEntry = true;
     	            User user = new User(myUser);
     	            try {
     	                userBinder.writeBean(user);
     	              
     	                UserDAO userDAO = new UserDAO();
     	                userDAO.create(user);
     	                
     	                setUpStep2();
     	            }
     	       
     	           catch (ValidationException e1) {
     	                isValidEntry = false;
     	            } 
     	           catch (Exception e2) {
     	        	 
     	                e2.printStackTrace();
     	            }
     	           
     	            if (!isValidEntry) {
     	                Notification notification = new Notification("Ein oder mehrere Felder sind ungültig", Notification.Type.ERROR_MESSAGE);
     	                notification.setPosition(Position.BOTTOM_CENTER);
     	                notification.setDelayMsec(4000);
     	                notification.show(Page.getCurrent());
     	            }
     	            
     	            	
     	            }
                
                
         });}
          

	private void setUpStep2() {
        Notification notification = new Notification("Sie haben sich erfolgreich registriert", Notification.Type.ASSISTIVE_NOTIFICATION);
        notification.setPosition(Position.MIDDLE_CENTER);
        notification.setDelayMsec(2000);
        notification.show(Page.getCurrent());
        UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
	}
		
		
	
private List<Button> addButtons(Layout layout) {
    HorizontalLayout buttonContainer = new HorizontalLayout();
    layout.addComponent(buttonContainer);
    buttonContainer.setSizeFull();

    Button buttonTosetup = new Button("Zurück");
    buttonContainer.addComponent(buttonTosetup);
    buttonContainer.setComponentAlignment(buttonTosetup, Alignment.BOTTOM_LEFT);

    Button buttonToStep2 = new Button("Weiter");
    buttonContainer.addComponent(buttonToStep2);
    buttonContainer.setComponentAlignment(buttonToStep2, Alignment.BOTTOM_RIGHT);

    List l = new ArrayList();
    l.add(buttonTosetup);
    l.add(buttonToStep2);
    return l;
		}
	}


