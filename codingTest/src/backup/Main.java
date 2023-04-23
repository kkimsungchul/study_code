package backup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dx ={0,1,0,-1};
    static int[] dy ={1,0,-1,0};
    static int arr[][];
    static boolean visited[][];
    static int n,m;

    public static void main(String []args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        visited = new boolean[n][m];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for(int j=0;j<m;j++){
                arr[i][j] = Integer.parseInt(line.substring(j,j+1));
            }
        }
        bfs(0,0);
        System.out.println(arr[n-1][m-1]);
    }

    public static void bfs(int i,int j){
        Queue<int[]> quere = new LinkedList<>();
        visited[i][j]=true;
        quere.add(new int[]{i,j});
        while(!quere.isEmpty()){

            int[] now = quere.poll();
            visited[i][j] = true;
            for(int k=0;k<4;k++){   //상하좌우 탐색
                int x = now[0]+dx[k];
                int y = now[1]+dy[k];
                if(x>=0 && y>0 && x<n && y<m){//배열 크기를 넘어가면 안됨
                    if(arr[x][y] !=0 && !visited[x][y]){

                        arr[x][y] = arr[now[0]][now[1]]+1;//
                        quere.add(new int[]{x,y});
                    }
                }

            }

        }

    }
}