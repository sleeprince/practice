package java0522;

import java.util.Scanner;

public class Study11 {

	public static void main(String[] args) {
		
		Scanner a = new Scanner(System.in);
		boolean key = false;
		
		/***********************************************
		 * 문제10) 사용자 입력값을 받아 2차원 ★를 출력하시오.
		 * 단, while문 안에서 출력 코드를 작성할것.
		 * 출력 예시)
	     * 입력 출력
	     * (1) ★★★★★    
	     *     ★★★★
	     *     ★★★
	     *     ★★
	     *     ★
	     *     
	     * (2) ★
	     *     ★★
	     *     ★★★
	     *     ★★★★
	     *     ★★★★★
	     *     
	     * (3)     ★
	     *        ★★
	     *       ★★★
	     *      ★★★★
	     *     ★★★★★
	     *     
	     * (4) ★★★★★
	     *      ★★★★
	     *       ★★★
	     *        ★★
	     *         ★
		 ************************************************/
		String[][] arr1 = new String[5][5];
	    for(int i = 0; i < arr1.length; i++) {
	    	for(int j = 0; j < arr1[i].length; j++) {
	    		if(j > arr1[i].length - i - 1) {
	    			arr1[i][j] = " ";
	    		}else {
	    			arr1[i][j] = "★";
	    		}
	    	}
	    }  
		
		String[][] arr2 = new String[5][5];	    
	    for(int i = 0; i < arr2.length; i++) {
	    	for(int j = 0; j < arr2[i].length; j++) {
	    		if(j <= i) {
	    			arr2[i][j] = "★";
	    		}else {
	    			arr2[i][j] = " ";
	    		}
	    	}
	    }
	    
	    String[][] arr3 = new String[5][5];
	    for(int i = 0; i < arr3.length; i++) {
	    	for(int j = 0; j < arr3[i].length; j++) {
	    		if(j < arr3[i].length - i - 1) {
	    			arr3[i][j] = " ";
	    		}else {
	    			arr3[i][j] = "★";
	    		}
	    	}
	    } 
	    
	    String[][] arr4 = new String[5][5];
	    for(int i = 0; i < arr4.length; i++) {
	    	for(int j = 0; j < arr4[i].length; j++) {
	    		if(j >= i) {
	    			arr4[i][j] = "★";
	    		}else {
	    			arr4[i][j] = " ";
	    		}
	    	}
	    }  
		// 각각 arr에 값넣기 반복문!!
		
		while(true) {
			
			System.out.println("1 ~ 4 정수의 값을 입력하세요.");
			int input = a.nextInt();
			
			String[][] arr = new String[1][1];
			
			// 출력할 arr 데이터 교체
			if(input == 1) {
				arr = arr1;
			} else if(input == 2) {
				arr = arr2;
			} else if(input == 3) {
				arr = arr3;
			} else if(input == 4) {
				arr = arr4;
			}
			
			// 입력 받은 값에 때라 종료 또는 출력
			switch (input) { 
				case 1: 
				case 2: 
				case 3: 
				case 4: 
					 for(int i = 0; i < arr.length; i++) {
					    	for(int j = 0; j < arr[i].length; j++) {
					    		System.out.print(arr[i][j]);
					    	}
					    	System.out.println();
					    } 
					break; 
			default:
				System.out.println("잘못된 값입니다."); 
				key = true;
			}
			
			if(key) break;
			
		}

	}

}
