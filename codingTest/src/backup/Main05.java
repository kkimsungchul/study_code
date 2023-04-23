package backup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main05 {

    static ArrayList<Integer>[] list;
    static int []check;
    static boolean visited[];
    static boolean IsEven;
    public static void main(String[]args) throws IOException {
        //이분그래프란
        //그래프가 인접하지 않은 두개의 그룹으로 나눌수 있는지
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        for(int i=0;i<testCase;i++){
            String [] s = br.readLine().split(" ");
            int v = Integer.parseInt(s[0]);
            int e = Integer.parseInt(s[1]);
            list = new ArrayList[v+1];
            visited = new boolean[v+1];
            check = new int[v+1];
            IsEven=true;

            for(int j=1;j<=v;j++){
                list[j] = new ArrayList<>();
            }
            //에지 데이터 저장
            for(int j=0; j<e; j++){
                s = br.readLine().split(" ");
                int start = Integer.parseInt(s[0]);
                int end = Integer.parseInt(s[1]);
                list[start].add(end);
                list[end].add(start);
            }
            //모든 노드에서 DFS 실행
            for(int j=1; j<=v; j++){

                if(IsEven){
                    dfs(i);
                }else{
                    break;
                }
            }
            if(IsEven){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
    }

    public static void dfs(int node){
        visited[node] = true;
        for(int i : list[node]){    //인접리스트로 받아서 start 에서 연결된 모든 노드 탐색
            if(!visited[i]){
                //바로 직전에 있는 노드와 다른 집합으로 분류
                check[i]= (check[node]+1)%2;
                dfs(i);
            }else if(check[node]==check[i]){
                    IsEven=false;
            }
        }
    }
}
