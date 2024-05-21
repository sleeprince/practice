
public class practice {

	public static void main(String[] args) {
		int[] arr = new int[10];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = arr.length - i - 1;
		}
		for(int j = 0; j < arr.length; j++) {
			arr[j] -= 9;
			System.out.println(arr[j]);
		}
	}
}
