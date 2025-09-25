/**

1. 로봇은 사방으로 이동할 수 있음
2. 로봇은 최단경로로 이동
    - r좌표 이동을 c좌표 보다 먼저 이동

t=0
시간의 흐름을 while문으로 돌리기
     t초에서 몇개 위치에서 충돌이 발생하는지 체크하기
    
t++;



**/
import java.util.*;

class Solution {
    static class Robot{
        int y=0;
        int x=0;
        int dstIdx = 0;
        List<int[]> dstNodes;
        public Robot(int y, int x, int dstIdx, List<int[]> dstNodes){
            this.y = y;
            this.x = x;
            this.dstIdx = dstIdx;
            this.dstNodes = dstNodes;
        }
        
        // true반환하면 맵에 존재, false 반환하면 맵에서 제거
        public boolean move(){
            int dstY = dstNodes.get(dstIdx)[0];
            int dstX = dstNodes.get(dstIdx)[1];
            int dy = 0;
            int dx = 0;
            
            if(y == dstY && x == dstX){
                // 더 이상 볼 목적지가 없으면 제거!
                if(dstNodes.size() == (dstIdx+1)){
                    return false;
                }
                // 다음 목적지 가즈아!
                dstIdx += 1;
                dstY = dstNodes.get(dstIdx)[0];
                dstX = dstNodes.get(dstIdx)[1];
            }
            
            if(y < dstY){
                dy  = 1;
            } else if(y > dstY){
                dy = -1;
            } else if(x < dstX){
                dx = 1;
            } else if(x > dstX){
                dx = -1;
            }
            
            y += dy;
            x += dx; 
            return true;
        }
    }

    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        
        List<Robot> robots = new ArrayList<>();
        
        // 로봇 셋팅
        for(int i=0; i<routes.length; i++){
            // System.out.println("route->"+routes[i]);
            // System.out.println("point->"+points[routes[i][0]-1]);
            int y = points[routes[i][0]-1][0];
            int x = points[routes[i][0]-1][1];
            List<int[]> dstNodes = new ArrayList<>();
            for(int j=1; j<routes[i].length; j++){
                // System.out.println("point->"+points[routes[i][j]-1]);
                int[] dst = {points[routes[i][j]-1][0], points[routes[i][j]-1][1]};
                dstNodes.add(dst);
            }
            // System.out.println(dstNodes.toString());
            Robot robot = new Robot(y,x,0,dstNodes);
            robots.add(robot);
        }
        
        
        int t= 0;
        
        while(!robots.isEmpty()){
            
            int cnt = checkRobots(robots);
            answer += cnt;
            // System.out.println("t->"+t+" cnt->"+cnt+" answer->"+answer);
            
            int robotsCnt = robots.size();
            while(robotsCnt>0){
                boolean isOn = robots.get(robotsCnt-1).move();
                if(!isOn){
                    // 로봇 제거
                    robots.remove(robotsCnt-1);
                }
                robotsCnt--;
            }
            
            t++;
        }
        
        return answer;
    }
    
    private int checkRobots(List<Robot> robots){
        int[][] mp = new int[104][104];
        int cnt = 0;
        for(Robot r : robots){
            if(mp[r.y][r.x] == 0){
                mp[r.y][r.x] = 1;
            } else if(mp[r.y][r.x] == 1){ // 위험상황 발생!!
                mp[r.y][r.x] = 2;
                cnt++;
            } // 동일위치에서 다시 위험상황 발생은 무시한다.
        }
        return cnt;
    }
}