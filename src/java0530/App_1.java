package java0530;

public class App_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String address = "jdbc:mariadb://localhost:3306/study";
		String user = "root";
		String password = "1q2w3e4r";
		new AppController_1().go(address, user, password);;
	}

}
