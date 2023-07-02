import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Num4 {
    final int NUM_OF_A=65;
    final int NUM_OF_Z=90;
    final int NUM_OF_a=97;
    final int NUM_OF_z=122;
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
            smallAlpha.put((char)(i+NUM_OF_a),(char)(NUM_OF_z-i));
        }
        for(int i=0; i<ALPHACOUNT; i++){
            bigAlpha.put((char)(i+NUM_OF_A),(char)(NUM_OF_Z-i));
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
