import java.util.*;

class Solution {
    int[][] mp = new int[104][104];
    int[][] visited = new int[104][104];
    int N,M;
    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};
    
    public int[] solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        
        int[] answer = {};
        
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(maps[i].charAt(j)-'0' >= 1 && maps[i].charAt(j)-'0' <= 9){
                    mp[i][j] = maps[i].charAt(j)-'0';
                }
            }
        }
        
        List<Integer> li = new ArrayList<>();
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(visited[i][j] != 0 || mp[i][j]==0) continue;
                visited[i][j] = 1;
                // System.out.println(i+ " : "+j);
                int ret = dfs(i,j);
                li.add(ret);
            }
        }
        
        Collections.sort(li);
        if(li.isEmpty())
            li.add(-1);
        return li.stream().mapToInt(i -> i).toArray();
    }
    
    private int dfs(int y, int x){
        int ret = mp[y][x];
        for(int i=0; i<4; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(ny<0 || nx<0 || ny>=N || nx>=M || visited[ny][nx] != 0 || mp[ny][nx]==0) 
                continue;
            visited[ny][nx] = 1;
            ret += dfs(ny, nx);
        }
        return ret;
    }
}