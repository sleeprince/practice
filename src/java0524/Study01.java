package java0524;

public class Study01 {
	static int b = 0;
	public static void main(String[] args) {
		a();
	}
	static void a() {
		System.out.println("안녕!" + b);
		if(b < 4) {
			b++;
			a();
		}
	}
}
