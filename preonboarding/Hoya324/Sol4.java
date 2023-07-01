import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Sol4 {

    public static String solution(String word) {
        String result = "";
        int target;

        for (int i = 0; i < word.length(); i++) {
            target = word.charAt(i);
            if (target >= 65 && target <= 90) {
                result += (char) (90-(target - 65));
            }
            else if (target >= 97 && target <= 122) {
                result += (char) (122-(target - 97));
            }
            else {
                result += (char) target;
            }
        }

        return result;

    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String word = bufferedReader.readLine();

        System.out.println(solution(word));

    }
}
