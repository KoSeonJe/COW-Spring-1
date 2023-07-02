import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sol5 {
    private static final int fiftyThousandWON = 50000;
    private static final int tenThousandWON = 10000;
    private static final int fiveThousandWON = 5000;
    private static final int oneThousandWON = 1000;
    private static final int fiveHundredWON = 500;
    private static final int oneHundredWON = 100;
    private static final int fiftyWON = 50;
    private static final int tenWON = 10;
    private static final int oneWON = 1;

    public static int [] solution(int money) {
        int [] result = new int [9];
        int [] moneyList = {fiftyThousandWON, tenThousandWON, fiveThousandWON, oneThousandWON, fiveHundredWON, oneHundredWON, fiftyWON, tenWON, oneWON};
        int changeCount;
        int leftMoney = money;
        for (int i = 0; i < 9; i++) {
            changeCount = leftMoney / moneyList[i];
            result[i] += changeCount;
            leftMoney %= moneyList[i];
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int money = Integer.parseInt(bufferedReader.readLine());
        System.out.println(Arrays.toString(solution(money)));
    }
}
