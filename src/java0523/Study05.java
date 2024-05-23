package java0523;

import java.util.Scanner;

/*********************************
 * >> Console 입력 알아보기
 * Scanner 클래스와 반복문를 이용하여 계산기 만들기
 *********************************/

public class Study05 {
	public static void main(String[] args) {

		/** 객체 생성 **/
		Scanner sc = new Scanner(System.in);
		
		/** 객체 사용 **/
//		int a;
//		double b, c;
//		while(true) {
//			System.out.print("더하기:1, 빼 기:2, 곱하기:3, 나누기:4, 나머지:5, 나가기:6\n");
//			a = sc.nextInt();
//			if(a == 6) break;
//			System.out.println("연산될 수 입력:");
//			b = sc.nextDouble();
//			System.out.println("연산할 수 입력:");
//			c = sc.nextDouble();
//			System.out.println("");
//			switch(a) {
//				case 1: 
//					System.out.println(b + " + " + c + " = " + (b+c));
//					break;
//				case 2: 
//					System.out.println(b + " - " + c + " = " + (b-c));
//					break;
//				case 3: 
//					System.out.println(b + " × " + c + " = " + (b*c));
//					break;
//				case 4: 
//					System.out.println(b + " ÷ " + c + " = " + (b/c));
//					break;
//				case 5: 
//					System.out.println(b + " % " + c + " = " + (b%c));
//					break;
//				default:
//					System.out.println("정해진 값을 입력해 주세요.");
//			}
//			System.out.println("");
//		}
		
		String plus = "+";
		String minus = "-";
		String mult = "*";
		String div = "/";
		
		System.out.println("첫 번째 숫자를 입력하세요.");
		int a = sc.nextInt();
		System.out.println("연산자를 입력하세요.");
		String b = sc.next();
		System.out.println("두 번째 숫자를 입력하세요.");
		int c = sc.nextInt();
		
		String d = "";
		switch(b) {
			case "+":
				d = a + plus + c + "=" + (a+c);
				break;
			case "-":
				d = a + minus + c + "=" + (a-c);
				break;
			case "*":
				d = a + mult + c + "=" + (a*c);
				break;
			case "/":
				d = a + div + c + "=" + (a/c);
				break;
		}
		System.out.println(d);
		/** 객체 제거 **/
		sc.close();
	}			
		
}
