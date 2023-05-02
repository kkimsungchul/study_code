package com.jpabook;

public class TestMain {
    public static void main(String[]args){
        int num=200;
        int [] divede = {10,100,1000,10000};
        int answer = 0;
        //369에서만 박수침
        for(int i=1; i<=num; i++){
            int temp =i;
            if(temp%10==3 || temp%10==6 || temp%10==9){
                System.out.println(temp);
                answer++;
                continue;
            }else{
                for(int j=divede.length-1 ; j>=0; j--){
                    if(temp > divede[j]){
                        if(temp/divede[j]==3 || temp/divede[j]==6 || temp/divede[j]==9){
                            answer++;
                            break;
                        }else{
                            temp = temp%divede[j];
                        }

                    }
                }
            }
        }


        System.out.println(answer);
    }
}
