package java0523;

import java.util.Scanner;

public class Study04_1 {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String q1 = "당신의 이름은?";
		String q2 = "당신의 나이는?";
		String q3 = "당신의 성별은?";
		String q4 = "당신의 나라는?";
		String q5 = "당신의 살고 있는 곳은?";
		String q6 = "당신의 학력은?";
		String q7 = "당신의 취미는?";
		String q8 = "당신의 직업은?";
		String q9 = "당신은 일주일에 3회 이상 대화하는 상대가 있습니까?";
		String q10 = "당신은 하루에 세 끼를 다 챙겨 먹습니까?";
		
		String[][] arr = new String[][] {{q1,""},{q2,""},{q3,""},{q4,""},{q5,""},{q6,""},{q7,""},{q8,""},{q9,""},{q10,""}};
		
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i][0]);
			String answer = sc.nextLine();
			arr[i][1] = answer;
		}
		
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i][0] + "\t→\t");
			System.out.println(arr[i][1]);
		}
		sc.close();
	}
}
