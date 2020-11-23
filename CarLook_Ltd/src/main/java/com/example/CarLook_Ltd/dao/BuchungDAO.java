/**
 * 
 */
package com.example.CarLook_Ltd.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.CarLook_Ltd.dto.BookingDetail;
import com.example.CarLook_Ltd.dto.User;
import com.example.CarLook_Ltd.entities.Booking;

/**
 * @author kpierz2s
 *
 */
public class BuchungDAO extends AbstractDAO{
	public static BuchungDAO dao = null;
	
	private BuchungDAO () {
		
	}
	
	public static BuchungDAO getInstance () {
		if (dao == null ) {
			dao = new BuchungDAO();
		}
		return dao;
	}
	
	public boolean addBooking ( Booking booking) {
		String sql = "insert into kpierz2s.booking values (?,?,default);";
		PreparedStatement statement = this.getPreparedStatement(sql);
		
		// Zeilenweise Abbildung der Datenbanksätze
		
		try {
		
			statement.setString(1, booking.getUser().getLogin());
			
			statement.setInt(2, booking.getAuto().getId());
			
			statement.executeUpdate();
		
		
			return true;
			
		} catch (SQLException ex) {
			Logger.getLogger(BuchungDAO.class.getName()).log(Level.SEVERE,null,ex);
			return false;
		}
		
		
	}

	
	public List<BookingDetail> getAllBookingsForUser(User user) {
		
		System.out.println(user.getLogin());
		
		Statement statement = this.getStatement();
		ResultSet rs = null;
		
		try {
			rs = statement.executeQuery("SELECT * "
										+ "FROM kpierz2s.booking JOIN kpierz2s.auto "
										+ "ON ( kpierz2s.booking.autoid = kpierz2s.auto.id )"
										+ "WHERE kpierz2s.booking.userid = \'" + user.getLogin() + "\'  " );
					
					
		} catch (SQLException ex) {
			Logger.getLogger(BuchungDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		if ( rs == null ) {
			
		
			return null;
			}
		
		List<BookingDetail> liste = new ArrayList<BookingDetail>();
		BookingDetail booking = null;
		
		try {
			while (rs.next()) {
				
				
				booking = new BookingDetail();
				booking.setAuto(rs.getString(1));
				booking.setId(rs.getInt(2));
				booking.setBookingid(rs.getInt(3));
				
			
			
				liste.add(booking);
				
			
			}
		}	catch (SQLException ex) {
			Logger.getLogger(BuchungDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		
		return liste;
	}
	
	
}
