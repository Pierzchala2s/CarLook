/**
 * 
 */
package com.example.CarLook_Ltd.dao;

/**
 * @author kpierz2s
 *
 */
	import java.sql.Statement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.List;
	import com.example.CarLook_Ltd.Exceptions.DatabaseException;
	import com.example.CarLook_Ltd.dto.Auto;
	

	public class AutoDAO extends AbstractDAO{
		
		
		public static AutoDAO dao = null;
		
		public AutoDAO() {
			
		}
		public static AutoDAO getInstance() {
			if ( dao == null) {
				dao = new AutoDAO();
			}
			return dao;
		}
		
		public List<Auto> getAutoByMarke(String marke ) {
			Statement statement = this.getStatement();
			ResultSet rs = null;
			
			try {
				rs = statement.executeQuery("SELECT * "
						+"FROM kpierz2s.auto " 
						+"WHERE kpierz2s.auto.marke = \'" + marke.toUpperCase() + "\'");
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			if ( rs == null)  	return null;
			
			List<Auto> liste = new ArrayList<Auto>();
			Auto auto = null;
			try {
				while (rs.next()) {
					
					auto = new Auto();
					auto.setId(rs.getInt(1));
					auto.setMarke(rs.getString(2));
					auto.setBaujahr(rs.getString(3));
					auto.setDescription(rs.getString(4));
					liste.add(auto);
				}	
			} catch  (SQLException ex) {
				
			}
			return liste;
		}

		
		 public List<Auto> retrieveAutos(String attribute) throws DatabaseException, SQLException {
		        Statement statement = this.getStatement();
		        ResultSet resultSet = null;
		        
		        //language=PostgreSQL
		        String insert = "SELECT * " +
		                "FROM \"kpierz2s\".auto " +
		                "WHERE marke LIKE '%" + attribute.toUpperCase() + "%'";
		        resultSet = statement.executeQuery(insert);
		        List<Auto> liste = new ArrayList<>();
		        Auto dto = null;

		        try {
		            while (resultSet.next()) {
		               dto = new Auto();
		              dto.setId(resultSet.getInt(1));
						dto.setMarke(resultSet.getString(2));
						dto.setBaujahr(resultSet.getString(3));
						dto.setDescription(resultSet.getString(4));
						liste.add(dto);
		               
		            }
		            
		        } catch (Exception e) {
		           
		            //throw new DatabaseException
		        }
		        return liste;
		    }
		
		
		
	}
	
	
	

