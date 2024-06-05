/**
1. 레버를 찾기
2. 출구를 찾기

출구는 레버가 당겨지지 않아도 지나갈 수 있다 == 일반적인 통로처럼 사용할 수 있다.

즉 S -> E -> L -> E로 이동할 수 있다!

큐에 위치를 넣어서 특정 y,x에 도달하는 최소 시간을 정함.
이때, isGetLever가 false라면 L를 위해 찾고,
isGetLever가 true라면 E를 위해 찾는다.

방문한 위치를 또 방문하지 않게하려면 visited[][]를 업데이트하는데
isGetLever가 바뀔 때 Visited를 한번 클리어해야함.

**/

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
    int[] start = {0,0};
    int[] end = {0,0};
    int[] lever = {0,0};
    boolean isGetLever = false;
    int N,M;
    int[][] mp = new int[104][104];
    int[] dy = {0,0,-1,1};
    int[] dx = {-1,1,0,0};
    int[][] visited = new int[104][104];
    public int solution(String[] maps) {
        int answer = 0;
        N = maps.length;
        M = maps[0].length();
        
        for(int i=0; i<maps.length; i++){
            for(int j=0; j<maps[0].length(); j++){
                if(maps[i].charAt(j) == 'S'){
                    start[0] = i;
                    start[1] = j;
                } else if(maps[i].charAt(j) == 'E'){
                    end[0] = i;
                    end[1] = j;
                } else if(maps[i].charAt(j) == 'L'){
                    lever[0] = i;
                    lever[1] = j;
                } else if(maps[i].charAt(j) == 'X'){
                    mp[i][j] = -1;
                }
            }
        }
        
        // 레버 찾아 삼만리
        Queue<Node> qu = new LinkedList<>();
        qu.add(new Node(start[0], start[1]));
        visited[start[0]][start[1]] = 1;
        while(!qu.isEmpty()){
            Node node = qu.poll();
            for(int i=0; i<4; i++){
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];
                
                // 지도 밖이나 벽으론 가지 못함. 방문했으면 가지 못함.
                if(ny<0 || nx<0 || ny>=N || nx >=M || mp[ny][nx]==-1 || visited[ny][nx]>0) continue;
                visited[ny][nx] = visited[node.y][node.x] + 1;
                if(ny == lever[0] && nx == lever[1]){
                    isGetLever = true;
                    break;
                }
                qu.add(new Node(ny, nx));
            }
            if(isGetLever) break;
        }
        if(!isGetLever)
            return -1;
        
        int leverTime = visited[lever[0]][lever[1]];
        visited = new int[104][104];
        visited[lever[0]][lever[1]] = leverTime;
        qu = new LinkedList<>();
        qu.add(new Node(lever[0], lever[1]));
        boolean isFindEnd = false;
        while(!qu.isEmpty()){
            Node node = qu.poll();
            for(int i=0; i<4; i++){
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];
                
                // 지도 밖이나 벽으론 가지 못함. 방문했으면 가지 못함.
                if(ny<0 || nx<0 || ny>=N || nx >=M || mp[ny][nx]==-1 || visited[ny][nx]>0) continue;
                visited[ny][nx] = visited[node.y][node.x] + 1;
                if(ny == end[0] && nx == end[1]){
                    isFindEnd = true;
                    break;
                }
                qu.add(new Node(ny, nx));
            }
            if(isFindEnd) break;
        }
        if(isFindEnd){
            return visited[end[0]][end[1]]-1;    
        } else{
            return -1;
        }
        
    }
}