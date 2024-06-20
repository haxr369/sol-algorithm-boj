/**
각 수레들은 자신의 시작 칸에서부터 자신의 도착 칸까지 이동해야 합니다.
각자의 도착 칸으로 이동시키면 퍼즐을 풀 수 있습니다.

턴마다 모든 수레를 상하좌우로 인접한 칸 중 한 칸으로 움직여야 합니다.

조건
1. 판 밖으로 이동 X
2. 방문했던 칸으로 이동 X
3. 도착 칸에 위치한 수레는 이동 X and 계속 고정
4. 동시에 두 수레를 같은 칸으로 이동 X
5. 

만나는 조건을 생각하면 너무 어려움
-> 모든 경로를 저장하고 경로와 경로를 비교하자
**/
import java.util.*;

class Point{
    int y;
    int x;
    public Point(int y, int x){
        this.y = y;
        this.x = x;
    }
    public boolean equal(Point b){
        return y == b.y && x == b.x;
    }
}

class Wagon{
    Point p;
    int color; // 0이면 빨강, 1이면 파랑
    List<Point> path = new ArrayList<>(); // 지금까지 경로
    int[][] visited = new int[5][5]; // 지금까지 방문 위치
    public Wagon(int y, int x, int color){
        this.p = new Point(y, x);
        this.color = color;
        visited[y][x] = 1;
        path.add(p);
    }
    public Wagon(int y, int x, int color, List<Point> path, int[][] visited){
        this.p = new Point(y, x);
        this.color = color;
        this.path = path;
        this.visited = visited;
    }
    
    public String toString(){
        return "y : "+p.y+" x : "+p.x+" color : "+color;
    }
    public Wagon goTo(int y, int x){
        List<Point> newPath = new ArrayList<>(path);
        int[][] newVisited = new int[5][5];
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                newVisited[i][j] = visited[i][j];
            }
        }
        newPath.add(new Point(y, x));
        return new Wagon(y, x, color, newPath, newVisited);
    }
}

class Solution {
    int N =0 ;
    int M= 0;

    
    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,-1,1};
    int[][] posi = new int[4][2];
    int[][] mp;
    public int solution(int[][] maze) {
        int answer = Integer.MAX_VALUE;
        N = maze.length;
        M = maze[0].length;
        mp = new int[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(maze[i][j] != 0 && maze[i][j] != 5){
                    posi[(maze[i][j] - 1)][0] = i;
                    posi[(maze[i][j] - 1)][1] = j;
                }
                mp[i][j] = maze[i][j];
            }
        }
        
        // a의 모든 경로 구하기
        List<List<Point>> redPaths = new ArrayList<>();
        Wagon wagon = new Wagon(posi[0][0], posi[0][1], 0);
        
        getPath(redPaths, wagon);
        // System.out.println("red ---- ");
        // for(int i=0; i<redPaths.size(); i++){
        //     for(Point j : redPaths.get(i)){
        //         System.out.print(j.y+ " : "+j.x + "    ");
        //     }
        //     System.out.print("\n");
        // }
        
        List<List<Point>> bluePaths = new ArrayList<>();
        Wagon wagon1 = new Wagon(posi[1][0], posi[1][1], 1);
        
        getPath(bluePaths, wagon1);
        // System.out.println("blue ---- ");
        // for(int i=0; i<bluePaths.size(); i++){
        //     for(Point j : bluePaths.get(i)){
        //         System.out.print(j.y+ " : "+j.x + "    ");
        //     }
        //     System.out.print("\n");
        // }
        
        for(List<Point> redPath : redPaths){
            for(List<Point> bluePath : bluePaths){
                int idx = 0;
                
                boolean isVaild = true;
                while(idx < redPath.size() || idx < bluePath.size()){
                    // 마지막 위치로 가면 움직임을 멈추기
                    int redIdx = Math.min(idx, redPath.size()-1);
                    int blueIdx = Math.min(idx, bluePath.size()-1);
                    // 서로 같은 자리로 간 경우
                    if(redPath.get(redIdx).equal(bluePath.get(blueIdx))){
                        isVaild = false;
                        break;
                    }
                    // 서로 자리가 바뀐 경우
                    if(idx >= 1 && redPath.get(redIdx).equal(bluePath.get(blueIdx-1)) 
                       && redPath.get(redIdx-1).equal(bluePath.get(blueIdx))){
                        isVaild = false;
                        break;
                    }
                    idx++;
                }
                
                if(isVaild){
                    answer = Math.min(answer, Math.max(redPath.size(), bluePath.size()));
                }
            }
        }
    
        if(answer == Integer.MAX_VALUE){
            return 0;
        } else{
            return answer - 1;
        }
    }
    
    public void getPath(List<List<Point>> path, Wagon wagon){
        
        // // 목표 위치에 도착한다면, 지금까지 경로를 추가한다.
        // if(wagon.p.y == posi[wagon.color + 2][0] && 
        //    wagon.p.x == posi[wagon.color + 2][1]){
        //    path.add(wagon.path); 
        // }
        
        Queue<Wagon> qu = new LinkedList<>();
        qu.add(wagon);
        int cnt = 0;
        while(!qu.isEmpty()){
            Wagon w = qu.poll();
            // System.out.println("현재 위치 y : "+w.p.y+" : "+w.p.x);
            for(int i=0; i<4; i++){
                int ny = w.p.y + dy[i];
                int nx = w.p.x + dx[i];
                if(ny < 0 || nx < 0 || ny >= N || nx >= M)
                    continue;
                if(w.visited[ny][nx] != 0 || mp[ny][nx]==5)
                    continue;
                Wagon newWagon = w.goTo(ny, nx);
                
                newWagon.visited[ny][nx] = w.visited[w.p.y][w.p.x] + 1;
                if(newWagon.p.y == posi[newWagon.color + 2][0] &&
                  newWagon.p.x == posi[newWagon.color + 2][1]){

                    path.add(newWagon.path);
                } else
                    qu.add(newWagon);
            }
            cnt++;
            if(cnt > 60){
                break;
            }
        }
        
    }
    
    
}

// public int dfs(int y, int x, int color){
//         System.out.println("-------------- y: "+y+" x : "+x+" color : "+color+ " 시간 : "+visited[y][x][color]);
//         // 빨강 수레나 파랑 수레가 도착점에 옴
//         System.out.println(positions[color+2].toString());
//         if(y == positions[color+2].y && 
//            x == positions[color+2].x && 
//            color == positions[color+2].color){
//             if(color==0){ // 빨강 수레가 도착했다면, 파랑수레를 출발 시키기
//                 System.out.println("빨강 위치로 도달! 시간 : "+visited[y][x][0]);
//                 return dfs(positions[1].y , positions[1].x, 1);
//             } else{
//                 // 빨강 도착 시간과 파랑 도착 시간 중 더 큰 값을 리턴하기
//                 System.out.println("파랑 위치로 도달! 시간 : "+visited[y][x][1]);
//                 return Math.max(visited[positions[1].y][positions[1].x][1] , 
//                                visited[positions[0].y][positions[0].x][0]);
//             }
//         }
//         int ret = Integer.MAX_VALUE;
//         for(int d=0; d<4; d++){
//             int ny = y+dy[d];
//             int nx = x+dx[d];
//             // 범위 밖, 방문한 위치, 벽, 다른 색 보다 늦거나 같게 도착한 경우는 스킥!
//             if(ny < 0 || nx < 0 || ny >= N || nx >= M ||
//               visited[ny][nx][color] !=0 || mp[ny][nx]==5 ||
//               (visited[ny][nx][(color+1)%2] != 0 &&
//                0 <= visited[y][x][color]+1 - visited[ny][nx][(color+1)%2] &&
//               visited[y][x][color]+1 - visited[ny][nx][(color+1)%2] <= 1))
//                 continue;
//             System.out.println("새로운 위치 y: "+ny+" x : "+nx+" color : "+color);
//             visited[ny][nx][color] = visited[y][x][color]+1;
//             ret = Math.min(dfs(ny, nx, color), ret);
//             visited[ny][nx][color] = 0;
//         }
//         return ret;
//     }
    