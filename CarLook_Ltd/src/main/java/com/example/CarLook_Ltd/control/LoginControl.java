/**
 * 
 */
package com.example.CarLook_Ltd.control;
import java.sql.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.vaadin.ui.UI;
import com.example.CarLook_Ltd.Exceptions.DatabaseException;
import com.example.CarLook_Ltd.Exceptions.NoSuchUserOrPassword;
import com.example.CarLook_Ltd.db.JDBCConnection;
import com.example.CarLook_Ltd.dto.User;
import com.example.CarLook_Ltd.util.Views;
import com.example.CarLook_Ltd.UI.MyUI;

/**
 * @author kpierz2s
 *
 */
public class LoginControl {
	
	public static void checkAuthentfication ( String login, String password ) throws NoSuchUserOrPassword, DatabaseException {
		
		
		ResultSet set = null;
		
		try {
			Statement statement = JDBCConnection.getInstance().getStatement();
			set = statement.executeQuery("SELECT * "
										+"FROM kpierz2s.user " 
										+"WHERE kpierz2s.user.email = \'" + login + "\'"
										+" AND kpierz2s.user.passwort = \'" + password + "\'");
			
			
		}catch (SQLException ex) {
			Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE,null,ex);
			throw new DatabaseException("Fehler im SQL Befehl! Bitte den Programmierer benachrichtigen!");
		}
		
		User user = null;
		try {
		if ( set.next()) {
			
			user = new User();
			user.setLogin(set.getString(1));
			user.setVorname(set.getString(3));
			} 
		 else {
			throw new NoSuchUserOrPassword();
		 }
		} catch (SQLException ex) {
			Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE,null,ex);
		}
		
		finally {
			JDBCConnection.getInstance().closeConnection();
		
		}
	
		//VaadinSession session = UI.getCurrent().getSession();
		//session.setAttribute(Roles.CURRENT_USER, user);
		
		((MyUI) UI.getCurrent()).setUser(user);
		
		UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
		
	
	}

	/**
	 * 
	 */
	public static void logoutUser() {
		UI.getCurrent().close();
		UI.getCurrent().getPage().setLocation("/CarLook");
		
		
	}

}
