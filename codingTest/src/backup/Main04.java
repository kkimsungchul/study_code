package backup;

import java.util.Scanner;

public class Main04 {
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        String [] splitData = s.split("-");


        int result=0;
        for(int i=0;i<splitData.length;i++){
            if(i==0){
                result +=sum(splitData[i]);
            }else{
                result -=sum(splitData[i]);
            }
        }

        System.out.println(result);
//10+20+30+40
//55-50+40

    }
    public static int sum (String str){
        int result=0;
        String [] temp = str.split("\\+");
        for(int i=0;i<temp.length;i++){
            result += Integer.parseInt(temp[i]);
        }
        return result;
    }
}
