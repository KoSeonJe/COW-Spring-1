import java.io.IOException;
import java.util.Scanner;

public class sub5 {

	public void main(String[] args) throws IOException {
		// 값 받기
		Scanner keyboard = new Scanner(System.in);
		int input=keyboard.nextInt();
		// solution에서 배열 리턴 받기
		// 배열 초기화 하는 법 -> int[] arr2={0,0,0}; 
		int[] arr=solution(input);
		//출력
		System.out.print("[");
		System.out.print(arr[0]);
		for(int i=1; i<9; i++) {
			System.out.print(",");
			System.out.print(arr[i]);
		}
		System.out.println("]");
	}

	private static int[] solution(int input) {
		int[] arr = new int[9];
		arr[0]=input/50000;
		input=input-arr[0]*50000;
		arr[1]=input/10000;
		input=input-arr[1]*10000;
		arr[2]=input/5000;
		input=input-arr[2]*5000;
		arr[3]=input/1000;
		input=input-arr[3]*1000;
		arr[4]=input/500;
		input=input-arr[4]*500;
		arr[5]=input/100;
		input=input-arr[5]*100;
		arr[6]=input/50;
		input=input-arr[6]*50;
		arr[7]=input/10;
		input=input-arr[7]*10;
		arr[8]=input/1;
		
		return arr;
		// TODO Auto-generated method stub
		
	}

}
