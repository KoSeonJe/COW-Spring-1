import java.util.Scanner;

public class Num2 {
    boolean flag =false;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder cryptogram = new StringBuilder(sc.next());
        Num2 num2 = new Num2();
        StringBuilder result = num2.Solution(cryptogram);
        System.out.println("\""+result+"\"");
        sc.close();

    }

    public StringBuilder Solution(StringBuilder cryptogram){
        flag=false;
        int cryptogramLength = cryptogram.length()-1;
        for(int i=0; i<cryptogramLength; i++){
            if(!(cryptogram.charAt(i)==cryptogram.charAt(i+1))){
                continue;
            }
            cryptogram.deleteCharAt(i);
            cryptogram.deleteCharAt(i);
            flag=true;
            cryptogramLength = cryptogram.length()-1;
        }
        if(!flag) {
          return cryptogram;
        }
        return Solution(cryptogram);
    }
}
