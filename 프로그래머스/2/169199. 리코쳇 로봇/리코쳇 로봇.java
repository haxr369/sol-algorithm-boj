import java.util.*;
class Node{
    int y;
    int x;
    public Node(int y, int x){
        this.y = y;
        this.x = x;
    }
}

class Solution {
    private int[] dy = {-1,1,0,0};
    private int[] dx = {0,0,-1,1};
    private int[][] mp = new int[104][104];
    private int[] robot = new int[2];
    private int[] dest = new int[2];
    private int[][] visited = new int[104][104];
    private int N,M;
    
    public int solution(String[] board) {
        int answer = 0;
        N = board.length;
        M = board[0].length();
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(board[i].charAt(j) == '.'){
                    mp[i][j] = 0;
                } else if(board[i].charAt(j) == 'R'){
                    robot[0] = i;
                    robot[1] = j;
                } else if(board[i].charAt(j) == 'G'){
                    dest[0] = i;
                    dest[1] = j;
                } else{
                    mp[i][j] = -1;
                }
            }
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                System.out.print(mp[i][j]);
            }
            System.out.println();
        }
        
        Queue<Node> qu = new LinkedList<>();
        visited[robot[0]][robot[1]] = 1;
        qu.add(new Node(robot[0], robot[1]));
        boolean isFind = false;
        while(!qu.isEmpty()){
            Node p = qu.poll();
            // System.out.println(p.y+" : "+p.x);
            for(int i=0; i<4; i++){
                int len = 1;
                int ny=0, nx=0;
                while(true){ // 벽에 닿을 때까지 전진
                    ny = p.y + dy[i]*len;
                    nx = p.x + dx[i]*len;
                    if(ny<0 || nx<0 || ny>=N || nx>=M || mp[ny][nx] == -1) {
                        len -=1;
                        break;
                    }
                    len++;
                }
                ny = p.y+dy[i]*len;
                nx = p.x+dx[i]*len;
               
                
                // 이미 방문한 위치라면 스킵
                if(visited[ny][nx] >= 1) continue;
                if(len == 0) continue; // 한칸도 전진하지 못했다.
                visited[ny][nx] = visited[p.y][p.x] + 1; // 방문 처리
                // System.out.println("--new -- "+ny+" : "+nx);
                if(ny == dest[0] && nx == dest[1]){
                    isFind = true;
                    break;
                }
                qu.add(new Node(ny, nx));
            }
            if(isFind) break;
        }
        
        return visited[dest[0]][dest[1]]-1;
    }
}