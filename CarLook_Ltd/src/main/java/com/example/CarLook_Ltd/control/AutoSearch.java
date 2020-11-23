/**
 * 
 */
package com.example.CarLook_Ltd.control;
import java.util.List;

import com.example.CarLook_Ltd.dao.AutoDAO;
import com.example.CarLook_Ltd.dto.Auto;





/**
 * @author kpierz2s
 *
 */
public class AutoSearch {
	
	 private AutoSearch () {
		
	}
	

	public static AutoSearch search = null;
	
	
	public static AutoSearch getInstance () {
		
		if (search == null) {
			search = new AutoSearch();
			
		}
		
		return search;
	}
	
	public List<Auto> getAutoByMarke( String marke) {
		return AutoDAO.getInstance().getAutoByMarke(marke);
	}
}
