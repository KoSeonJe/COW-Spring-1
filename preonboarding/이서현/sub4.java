import java.io.IOException;
import java.util.Vector;

public class sub4 {
	public static final int ascIIa=65;
	public static final int ascIIz=90;
	public static final int ascIIA=97;
	public static final int ascIIZ=122;

	public void main(String[] args) throws IOException {
		// 입력받고 vector에 저장
		Vector<Character> str = new Vector<Character>();
		int num=System.in.read();
		for(int i=0;num!=0x0d; i++) {
			str.add((char)num);
			num=System.in.read();
		}
		// solution에서 vector 반환
		str=solution(str);
		//출력
		for(int i=0; i<str.size(); i++) {
			System.out.print(str.get(i));
		}
	}

	private static Vector<Character> solution(Vector<Character> printstr) {
		
		Vector<Character> newstr = new Vector<Character>();
		
		for(int i=0; i<printstr.size();i++) {
			int numchar=(int)printstr.get(i);
			int startsubtract=numchar-ascIIa;
			int endsubtract = ascIIz-numchar;
			if(numchar>=ascIIA && numchar<=ascIIZ) {
				startsubtract=numchar-97;
				endsubtract=ascIIZ-numchar;
				if(startsubtract>12) { // a-z 총 26개 중앙값 13
					numchar=ascIIA+endsubtract;
				}else {
					numchar=ascIIZ-startsubtract;
				}
			}else if(numchar>=ascIIa && numchar<=ascIIz){
				if(startsubtract>12) {
					numchar=ascIIa+endsubtract;
				}else {
					numchar=ascIIz-startsubtract;
				}
			}else {
				//empty
			}
			newstr.add((char)numchar);
		}
		
		return newstr;
	}

}
