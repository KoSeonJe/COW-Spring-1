import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

public class Sol2 {

    private StringBuilder solution(String cryptogram) {
        Deque<Character> deque = new ArrayDeque<>();
        int cryptogramLength = cryptogram.length();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < cryptogramLength; i++) {
            char currentChar = cryptogram.charAt(i);
            boolean isNotEmpty = !deque.isEmpty();
            boolean isEqualsWithPrevCharAndCurrentChar = false;
            try {
                isEqualsWithPrevCharAndCurrentChar = (deque.getLast() == currentChar);
            } catch (NoSuchElementException ignored) {}

            if (isNotEmpty && isEqualsWithPrevCharAndCurrentChar) {
                deque.removeLast();
                continue;
            }
            deque.add(cryptogram.charAt(i));
        }

        for (char i : deque) {
            stringBuilder.append(i);
        }
        return stringBuilder;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String cryptogram = br.readLine();
        Sol2 so1 = new Sol2();
        System.out.println(so1.solution(cryptogram));
    }
}
