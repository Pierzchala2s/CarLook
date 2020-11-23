/**
 * 
 */
package com.example.CarLook_Ltd.dao;

import com.example.CarLook_Ltd.Exceptions.DatabaseException;
import com.example.CarLook_Ltd.db.JDBCConnection;
import com.example.CarLook_Ltd.dto.User;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;

/**
 * @author kpierz2s
 *
 */
public class UserDAO extends AbstractDAO{
	
	public UserDAO() throws DatabaseException {
        super();
    }
	
	public void create(User user) throws DatabaseException {
		String sql = "insert into kpierz2s.user values (?,?,?,?);";
		PreparedStatement statement = this.getPreparedStatement(sql);

		
			try {
				statement.setString(1,user.getEmail());
				statement.setString(2,user.getPassword());
				statement.setString(3,user.getVorname());
				statement.setString(4,user.getNachname());
				statement.executeUpdate();
			}
			
		
			catch (SQLException e) {
				Notification notification = new Notification("Email bereits verwendet", Notification.Type.ERROR_MESSAGE);
	              notification.setPosition(Position.BOTTOM_CENTER);
	              notification.setDelayMsec(4000);
	              notification.show(Page.getCurrent());
				Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE,null,e);
				throw new DatabaseException("Email wird bereits verwendet");
			}
		
		
		
     
    }
	


	protected User create(ResultSet resultSet) throws DatabaseException {
        User dto;
        try {
            dto = new User(resultSet.getString("username"),
                    resultSet.getString("email"),
                    resultSet.getString("passwort"));
            
            return dto;
        } catch (SQLException e) {
            throw new DatabaseException("Konnte keinen user erstellen" + e.getMessage());
        }
    
	
    }}

