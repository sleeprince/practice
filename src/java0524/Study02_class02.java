package java0524;

public class Study02_class02 extends Study02_class01{
	
	int a;
	
	Study02_class02(){}
	Study02_class02(int a){
		super.a = a;
	}
	
	void callFuction() {
		fuction();
	}
	void callA() {
		System.out.println(a);
	}

	@Override
	void fuction2() {
		
	}
}
