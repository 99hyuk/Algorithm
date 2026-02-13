import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int score[][];
    static boolean visited[];
    
    static int min = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        
        score = new int[n][n];
        visited = new boolean[n];
        
        for(int i=0; i<n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++)
            {
                score[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        dfs(0,0);
        System.out.println(min);
    }

    
   static void dfs(int idx, int depth)
   {
       if(depth == n/2)
       {
           diff();
           return;
       }
       
       for(int i=idx; i<n; i++)
       {
           if(!visited[i])
           {
               visited[i] = true; //방문으로 변경
               dfs(i+1,depth+1); //재귀 호출
               visited[i] = false; //재귀가 끝나면 비방문으로 변경
           }
       }
   }
    
   static void diff()
   {
       int start = 0;
       int link = 0;
       
       for(int i=0; i<n-1; i++)
       {
           for(int j=i+1; j<n; j++)
           {
               if(visited[i] == true && visited[j] == true)
               {
                   start +=score[i][j];
                   start +=score[j][i];
               }
               else if(visited[i]==false && visited[j]==false)
               {
            	   link +=score[i][j];
            	   link +=score[j][i];
            	   
               }
           }
       }
       
       int result = Math.abs(start-link);
       
       if(result ==0)
       {
    	   System.out.println(result);
    	   System.exit(0);
       }
       
       min = Math.min(result,min);
   }
    
    
}