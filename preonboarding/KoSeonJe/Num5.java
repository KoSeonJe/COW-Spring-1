import java.util.Arrays;
import java.util.Scanner;

public class Num5 {
    int money50 =50000;
    int money10 =10000;
    int money5 = 5000;
    int money1 = 1000;
    int coin500 = 500;
    int coin100 =100;
    int coin50 =50;
    int coin10 =10;
    int coin1 = 1;
    int moneyCount=9;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int money =sc.nextInt();
        Num5 num5 = new Num5();
        System.out.println(Arrays.toString(num5.Solution(money)));
        sc.close();
    }

    public int[] Solution(int money){
        int[] moneyTemp = {money50,money10,money5,money1,coin500,coin100,coin50,coin10,coin1};
        int[] moneyResult = new int[moneyCount];

        for(int i=0; i<moneyTemp.length; i++){
            moneyResult[i]=money/moneyTemp[i];
            money =money%moneyTemp[i];
        }
        return moneyResult;


    }
}
