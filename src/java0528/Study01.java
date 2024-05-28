package java0528;

import java.util.ArrayList;
import java.util.List;

public class Study01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String driver = "org.mariadb.jdbc.Driver";
		String address = "jdbc:mariadb://localhost:3306/study";
		String user = "root";
		String password = "1q2w3e4r";
				
		new DbController(driver, address, user, password);
	
		
		
	}

}
