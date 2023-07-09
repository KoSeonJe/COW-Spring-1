import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sol4 {
    private static final int asciiCodeOfA = 65;
    private static final int asciiCodeOfZ = 90;
    private static final int asciiCodeOfa = 97;
    private static final int asciiCodeOfz = 122;

    private StringBuilder solution(String word) {
        int currentCharToAscii;
        StringBuilder stringBuilder = new StringBuilder();
        int wordLength = word.length();

        for (int i = 0; i < wordLength; i++) {
            currentCharToAscii = word.charAt(i);
            boolean isUppercase = currentCharToAscii >= asciiCodeOfA && currentCharToAscii <= asciiCodeOfZ;
            boolean isLowercase =  currentCharToAscii >= asciiCodeOfa && currentCharToAscii <= asciiCodeOfz;
            if (isUppercase) {
                stringBuilder.append((char) (asciiCodeOfZ - (currentCharToAscii - asciiCodeOfA)));
            }
            else if (isLowercase) {
                stringBuilder.append((char) (asciiCodeOfz-(currentCharToAscii - asciiCodeOfa)));
            }
            else {
                stringBuilder.append((char) currentCharToAscii);
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String word = bufferedReader.readLine();
        Sol4 sol4 = new Sol4();
        System.out.println(sol4.solution(word));
    }
}
