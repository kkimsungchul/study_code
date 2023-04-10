package EffectiveJava.temp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TempMain {

    public static void main(String[]args) throws Exception{

        String path="C:\\Users\\USER\\Desktop\\kimsc\\0.깃허브-개발공부\\Git\\[Github] 깃허브 블로그 만들기(1.기본설치).txt";

        List<String> lines = Files.readAllLines(Paths.get(path));



        File file = new File(path);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        int lineLength =1;
        for(String line : lines){
            //사용하지 않는 우측 공백 제거
            line = line.replaceAll("\\s+$","");
            //공백을 넣는 이유는 markdown 에서 공백 두칸 후 엔터를입력해야 줄바꿈으로 인식함
            
            //엔터 여러줄 입력 방지
            if(lineLength==0){
                if(line.length()!=0){
                    fileWriter.write(line+"  ");
                    fileWriter.write("\n");
                    lineLength = line.length();
                }else{
                    lineLength = line.length();
                }
            }else{
                fileWriter.write(line+"  ");
                fileWriter.write("\n");
                lineLength = line.length();
            }

        }
        //파일 제일 하단에 {% endraw %} 추가
        fileWriter.close();

    }

}
