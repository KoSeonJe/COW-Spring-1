import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Sol2 {

    public static String solution(String cryptogram) {
        Stack<Character> stack = new Stack<>();
        String newCryptogram = "";

        for (int i = 0; i < cryptogram.length(); i++) {
            if (stack.size() != 0 && stack.peek() == cryptogram.charAt(i)) {
                stack.pop();
                continue;
            }
            stack.add(cryptogram.charAt(i));
        }

        for (char i : stack) {
            newCryptogram += i;
        }

        return newCryptogram;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String cryptogram = br.readLine();

        System.out.println(solution(cryptogram));


    }
}