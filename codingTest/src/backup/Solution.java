package backup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

    static boolean visited[];
    static ArrayList<Integer>[] A;
    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        visited = new boolean[n];
        A = new ArrayList[n];
        for(int i=0;i<n;i++){
            A[i] = new ArrayList<Integer>();
        }
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            A[start-1].add(end-1);
            A[end-1].add(start-1);
        }

        int count =0;

        for(int i=0;i<n;i++){
            if(!visited[i]){
                count++;
                dfs(i);
            }
        }
        System.out.println(count);
    }

    public static void dfs(int v){
        if(visited[v]){
            return;
        }else{
            visited[v]=true;
            for(int i: A[v]){
                if(!visited[i]){
                    dfs(i);
                }
            }
        }
    }

}