import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Num4 {
    final int NUMOFA=65;
    final int NUMOFZ=90;
    final int NUMOFa=97;
    final int NUMOFz=122;
    final int ALPHACOUNT=26;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder word = new StringBuilder(sc.nextLine());
        Num4 num4 = new Num4();
        System.out.println("\""+num4.Solution(word)+"\"");
        sc.close();
    }

    public StringBuilder Solution(StringBuilder word){

        Map<Character, Character> smallAlpha = new HashMap<>();
        Map<Character, Character> bigAlpha = new HashMap<>();
        for(int i=0; i<ALPHACOUNT; i++){
            smallAlpha.put((char)(i+NUMOFa),(char)(NUMOFz-i));
        }
        for(int i=0; i<ALPHACOUNT; i++){
            bigAlpha.put((char)(i+NUMOFA),(char)(NUMOFZ-i));
        }
        for(int i=0; i<word.length(); i++){
            if(word.charAt(i)==' ')continue;

            if(Character.isUpperCase(word.charAt(i))){
                word.replace(i,i+1, String.valueOf(bigAlpha.get(word.charAt(i))));
            }else{
                word.replace(i,i+1, String.valueOf(smallAlpha.get(word.charAt(i))));
            }
        }
        return word;
    }
}
