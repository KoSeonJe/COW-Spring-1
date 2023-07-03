import java.util.Scanner;

public class sub2 {

	static String printstr;
	public void main(String[] args) {
		//입력받기
		Scanner keyboard = new Scanner(System.in);
		String str =  keyboard.next();
		printstr=str;
		boolean end = false;
		//soultion에서 boolean 받아서 while문
		while(end!=true) {
			end=solution(keyboard);
		}
		//출력
		System.out.println(printstr);
		
	}
	public static boolean solution(Scanner keyboard) {
		int startindex=-2;
		int endindex=-2;
		int deletecount=0;
		for(int i=0; i<printstr.length()-1; i++) {
			char first = printstr.charAt(i);
			char two = printstr.charAt(i+1);
			if(first==two) {
				if(startindex==-2) {
					startindex=i;
				}
				endindex=i+1;
				if(i==printstr.length()-2) {
					printstr=printstr.substring(0, startindex)+printstr.substring(endindex+1);
				}
			}else {
				if(endindex!=-2) {
				printstr=printstr.substring(0, startindex)+printstr.substring(endindex+1);
//				System.out.println(printstr);
				deletecount++;
				break;
				}
			}
		}
		if(deletecount==0) {
			return true;
		}else {
			return false;
		}
	}
}
