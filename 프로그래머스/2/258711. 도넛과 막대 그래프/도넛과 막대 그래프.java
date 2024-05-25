/**
크기가 n인 도넛 모양 그래프 : 어디서 시작하든 n-1 이후에 자기 자신으로 돌아옴
크기가 n인 막대 모양 그래프 : 단 하나의 점에서 시작해서 전부 방문할 수 있음
크기가 n인 8자 모양 그래프 : 크기가 (n+1)인 2개의 도넛 모양 그래프에서 정점을 하나씩 골라 결합시킨 형태

이 그래프들과 무관한 정점을 하나 생성
생성한 정점의 번호와 정점을 생성하기 전 도넛 모양 그래프의 수, 막대 모양 그래프의 수, 8자 모양 그래프의 수

정점에서 자기 자신으로 다시 돌아옴 -> 큐를 이용하면 될듯
    재방문한 노드가 있을 때 다시 갈 수 있는 간선이 있는가?
        yes -> 8자
        no -> 도넛
막대 모양 정점 : 다음 정점으로 갈 수 없는 정점이 존재 -> 큐를 이용해도 괜찮음.

1. 도넛과 8자를 모두 찾기
2. 모든 위치 방문할 수 있는 정점과 그 정점과 연결된 간선 수 = 막대그래프 수

**/

import java.util.*;

class Solution {
    private int donat = 0;
    private int eight = 0;
    private int stick = 0;
    private int centralIdx;
    private int[] interLine = new int[1000004];
    private int[] exterLine = new int[1000004];
    private int maxIdx = 0;
    public int[] solution(int[][] edges) {
        
        for(int i=0; i<edges.length; i++){
            exterLine[edges[i][0]]++;
            interLine[edges[i][1]]++;
            maxIdx = Math.max(maxIdx, edges[i][0]);
            maxIdx = Math.max(maxIdx, edges[i][1]);
        }
        
        for(int i=1; i<= maxIdx; i++){
            if(exterLine[i]>=2 && interLine[i]==0){ // 중앙 노드 찾기
                centralIdx = i;
            } else if(exterLine[i] == 2 && interLine[i] >= 2){ // 8자
                eight++;
            } else if(exterLine[i]==0 && interLine[i] >= 1){ // 막대
                stick++;
            }
        }
        
        System.out.println("8자 개수 : "+eight);
        System.out.println("중앙 노드 : "+centralIdx);
        System.out.println("막대 개수 : "+stick);
        
    
        int[] answer = {centralIdx, (exterLine[centralIdx] - eight - stick), stick, eight};
        return answer;
    }


}