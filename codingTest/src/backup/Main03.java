package backup;

import java.util.Scanner;

public class Main03 {
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int count =0;
        int n = sc.nextInt();
        int target = sc.nextInt();
        int [] coin = new int[n];
        for(int i=0;i<n;i++){
            coin[i] = sc.nextInt();
        }
        for(int i=coin.length-1;i>=0;i--){
            count += target/coin[i];
            target = target%coin[i];
            if(target==0){
                break;
            }
        }
        System.out.println(count);

    }
}
