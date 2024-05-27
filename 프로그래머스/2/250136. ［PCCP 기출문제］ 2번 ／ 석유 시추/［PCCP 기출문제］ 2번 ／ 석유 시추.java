/**
석유는 상하좌우로 연결

한 열 끝까지 관이 이동 -> 관이 지나간 석유를 전부 빨아드림.
뽀을 수 있는 가장 많은 량

**/
import java.util.*;
class Solution {
    private int[] dy = {0,0,-1,1};
    private int[] dx = {1,-1,0,0};
    private int N=0, M=0;
    private int[] oils = new int[504];
    private int[][] visited = new int[504][504];
    
    private int Sx = 0;
    private int Ex = 0;
    private int[][] oilLand = new int[501][501];
    public int solution(int[][] land) {
        
        // 각 석유의 크기, 시작 Sx와 좌표 끝 Ex 좌표를 구
        // Sx부터 Ex까지 석유의 크기를 배열에 누적합 
        // 배열에서 최대 값을 찾기
        N = land.length;
        M = land[0].length;
        for(int n=0; n<N; n++){
            for(int m=0; m<M; m++){
                oilLand[n][m] = land[n][m];       
            }
        }
        int maxValue = 0;
        
        for(int n=0; n<N; n++){
            for(int m=0; m<M; m++){ // O(N^2)
                if(visited[n][m]==1 || land[n][m]==0) continue;
                Sx = m;
                Ex = m;
                visited[n][m] = 1;
                int ret = dfs(n, m);
                for(int i=Sx; i<=Ex; i++){
                    oils[i] += ret;
                    maxValue = Math.max(maxValue, oils[i]);
                }
            }
        }
    
        return maxValue;
    }
    
    private int dfs(int y, int x){
        int ret = 1;
        // 아무 곳도 갈 수 없다면 1을 출력
        // System.out.println("y : "+y+" x : "+x);
        Sx = Math.min(Sx, x);
        Ex = Math.max(Ex, x);
        for(int i=0; i<4; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(ny < 0|| nx < 0|| ny>=N || nx >= M) continue;
            if (visited[ny][nx]==1 || oilLand[ny][nx]==0) continue;
            visited[ny][nx] = 1;
            ret += dfs(ny, nx);
        }
        return ret;
    }
}