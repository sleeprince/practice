package java0523;

public class Study01 {
	public static void main(String[] args) throws Exception{
        int a = System.in.read();
        int sum = 1;
        for(int i = 0; i < 1000000; i++){
            a = System.in.read();
            if(a < 32) break;
            if(a == 32) sum++;
        }
        System.out.print(sum);
    }
}
