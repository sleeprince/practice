package java0523;

import java.util.Scanner;

/*********************************
 * >> Console 입력 알아보기
 * Scanner 클래스를 이용하여 설문조사 하기
 *********************************/

public class Study04 {
	private static void timeBuffer(int t) {
		long start = System.currentTimeMillis();
		long end = System.currentTimeMillis();
		while(end - start < t*1000) {
			end = System.currentTimeMillis();
		}
	}
	public static void main(String[] args) {

		/** 객체 생성 **/
		Scanner sc = new Scanner(System.in);
		String str;
		int cnt = 0;
		/** 객체 사용 **/
			System.out.println("사람은 한 뼘의 앞날, 아니 손가락 한 마디의 앞날도 모르는 법이라죠.");
			timeBuffer(1);
			System.out.println("하지만 이제부터 제가 당신을 앞날을 알려드리겠습니다.");
			timeBuffer(1);
			System.out.println("그래요. 지금 모니터의 흰빛을 바라보며 못 미덥다는 듯 눈빛을 보내는 당신 말이에요.");
			timeBuffer(1);
			System.out.println("제 말이 틀렸나요?");
			timeBuffer(1);
			System.out.println("“예”, “아니오”로 대답해 봐요.");
			str = sc.nextLine();
			while(!"예".equals(str) && !"아니오".equals(str)) {
				if("응".equals(str)) {
					System.out.println("“응”은 반말이고요.");
					timeBuffer(1);
				}else if("아니".equals(str)) {
					System.out.println("“아니”는 반말이고요.");
					timeBuffer(1);
				}
				if(cnt < 3) {
					System.out.println("“예”, “아니오”로 대답하라니까요.");
					cnt++;
				}else {
					System.out.println("이젠 슬슬 지치는군요. 당신은 귀머거리인가요?");
					timeBuffer(1);
					System.out.println("“예”, “아니오”로 대답해요.");
				}
				str = sc.nextLine();
			}
			System.out.println("그렇지! 저는 이미 당신이 “" + str + "”라고 말할 줄 알았어요.");
			timeBuffer(1);
			System.out.println("혹시 소름 돋았나요?");
			System.out.println("(예/아니오)");			
			str = sc.nextLine();
			System.out.println("역시 그럴 줄 알았죠.");
			timeBuffer(1);
			System.out.println("당신, 집에 개 키우죠?");
			timeBuffer(1);
			System.out.println("(예/아니오)");			
			str = sc.nextLine();
			cnt = 0;
			while(!"예".equals(str) && !"아니오".equals(str)) {
				if(cnt < 3) {
					System.out.println("쓸데없는 소리 말고, “예”, “아니오”로만 대답해요.");
					cnt++;
				}else {
					System.out.println("이젠 슬슬 지치는군요. 당신은 귀머거리인가요?");
					timeBuffer(1);
					System.out.println("“예”, “아니오”로 대답해요.");
				}
				str = sc.nextLine();
			}
			if("예".equals(str)) {
				System.out.println("거 봐요, 내 말 맞았죠?");
				timeBuffer(1);
				System.out.println("이제는 날 믿겠나요?");
			}else {
				System.out.println("아… 다행이네요.");
				timeBuffer(1);
				System.out.println("만일 개를 키웠더라면 곧 큰일이 벌어졌을 거예요.");
			}

		
//		System.out.println("input 10 to 1:");
//		int a = sc.nextInt();
//		switch(a){
//			case 10:
//				System.out.println("10");
//				timeBuffer(1);
//			case 9:
//				System.out.println("9");
//				timeBuffer(1);
//			case 8:
//				System.out.println("8");
//				timeBuffer(1);
//			case 7:
//				System.out.println("7");
//				timeBuffer(1);
//			case 6:
//				System.out.println("6");
//				timeBuffer(1);
//			case 5:
//				System.out.println("5");
//				timeBuffer(1);
//			case 4:
//				System.out.println("4");
//				timeBuffer(1);
//			case 3:
//				System.out.println("3");
//				timeBuffer(1);
//			case 2:
//				System.out.println("2");
//				timeBuffer(1);
//			case 1:
//				System.out.println("1");
//				timeBuffer(1);
//			case 0:
//				System.out.println("0");
//				timeBuffer(1);
//			default:
//				System.out.println("times up!");
//		}
		
		/** 객체 제거 **/
		sc.close();
	}

}
