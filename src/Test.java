
public class Test {
	
	public static void main(String[] args) throws Exception {

		char[][] arr = new char[9][17];
		
		for(int i = 0; i < arr.length ; i++) {	
			
			for(int j = 0; j < arr[i].length ; j++) {
				if(j%2==1) {
					arr[i][j] = 32;					
				}else if(j == 2*i) {
					arr[i][j] = '8';
				}else {
					arr[i][j] = '0';
				}				
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
	}
}