
public class Private_Test1 {
		
	public static void main(String[] args) {
char[][] arr = new char[9][17];
		
		for(int i = 0; i < arr.length ; i++) {			
			for(int j = 0; j < arr[i].length ; j++) {
				if(j%2==1) {
					arr[i][j] = 32;
				}else if(i == arr.length - 2) {
					arr[i][j] = 32;
				}else if(i%3 == 0 && j>=2*i && j < 2*i+6) {
					arr[i][j] = '3';
				}else {
					arr[i][j] = '0';
				}				
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
    }
}
