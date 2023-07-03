import java.util.Arrays;
import java.util.Scanner;

public class Num5 {
    final int MONEY50 =50000;
    final int MONEY10 =10000;
    final int MONEY5 = 5000;
    final int MONEY1 = 1000;
    final int COIN500 = 500;
    final int COIN100 =100;
    final int COIN50 =50;
    final int COIN10 =10;
    final int COIN1 = 1;
    final int MONEYCOUNT=9;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int money =sc.nextInt();
        Num5 num5 = new Num5();
        System.out.println(Arrays.toString(num5.Solution(money)));
        sc.close();
    }

    public int[] Solution(int money){
        int[] moneyTemp = {MONEY50,MONEY10,MONEY5,MONEY1,COIN500,COIN100,COIN50,COIN10,COIN1};
        int[] moneyResult = new int[MONEYCOUNT];

        int moneyTempLength = moneyTemp.length;
        int leftmoney =money;
        for(int i=0; i<moneyTempLength; i++){
            moneyResult[i]=leftmoney/moneyTemp[i];
            money =money%moneyTemp[i];
            leftmoney=money;
        }
        return moneyResult;


    }
}
