/**

특정 숫자를 만들 때 최소 횟수를 저장하기

bfs를 이용하지만,
    처음 방문한 것만 가기

**/
import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        int answer = 0;
        int[] visited = new int[1000004];
        if(x==y)
            return 0;
        Queue<Integer> qu = new LinkedList<>();
        qu.add(x);
        visited[x] = 1;
        boolean isFind = false;
        while(!qu.isEmpty()){
            int p = qu.poll();
            
            if(p+n <= 1000000 && visited[p+n]==0){
                visited[p+n] = visited[p] + 1;
                qu.add(p+n);
                if(p+n == y) isFind= true;
            }
            
            if(p*2 <= 1000000 && visited[p*2]==0){
                visited[p*2] = visited[p] + 1;
                qu.add(p*2);
                if(p*2 == y) isFind= true;
            }
            
            if(p*3 <= 1000000 && visited[p*3]==0){
                visited[p*3] = visited[p] + 1;
                qu.add(p*3);
                if(p*3 == y) isFind= true;
            }
            if(isFind) break;
        }
        
        
        if(isFind){
            return visited[y]-1;
        } else{
            return -1;
        }
    }
}