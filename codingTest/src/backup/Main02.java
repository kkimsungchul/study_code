package backup;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class Main02 {
    public static void main(String[]args)throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int []dataArr = new int[n];
        for(int i=0;i<n;i++){
            dataArr[i] = sc.nextInt();
        }
        Arrays.sort(dataArr);

        int m = sc.nextInt();
        for(int i=0;i<m;i++){
            boolean find = false;
            int target = sc.nextInt();
            int left = 0;
            int right = dataArr.length-1;
            while (left<=right){
                int mid = (left+right)/2;
                int midValue = dataArr[mid];
                if(midValue>target){
                    right = mid -1;
                }else if(midValue<target){
                    left = mid +1;
                }else{
                    find=true;
                    break;
                }
            }
            if(find){
                System.out.println(1);
            }else{
                System.out.println(0);
            }
        }










    }
}
