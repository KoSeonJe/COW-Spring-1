import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sol5 {


    public static int [] solution(int money) {

        int [] result = {0,0,0,0,0,0,0,0,0};
        int [] moneyList = {50000, 10000, 5000, 1000, 500, 100, 50, 10, 1};
        for (int i = 0; i < 9; i++) {
            result[i] += (money / moneyList[i]);
            money %= moneyList[i];
        }

        return result;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int money = Integer.parseInt(bufferedReader.readLine());

        System.out.println(Arrays.toString(solution(money)));
    }
}
