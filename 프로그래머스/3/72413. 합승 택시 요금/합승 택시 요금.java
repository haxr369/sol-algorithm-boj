/***

1. 모든 점부터 모든 점까지 가는 길에 최소 거리 구하기

2. S->CN 까지 거리 d1
    CN->a = d2 = a->CN
    CN->b = d3 = b->CN
    
n <= 200
n x (n-1) / 2 = 200*199/2 = 19,900

19,900 * 100,000 = 1,900,900,000

***/
import java.util.*;
class Solution {

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        int[][] minDist = new int[n+4][n+4];
        List<List<Integer>> mp = new ArrayList<>();
        
        for(int i=0; i<n+4; i++){
            Arrays.fill(minDist[i], Integer.MAX_VALUE);
            List<Integer> tmp = new ArrayList<>();
            mp.add(tmp);
        }
        
        for(int i=0; i<fares.length; i++){
            int n1 = fares[i][0];
            int n2 = fares[i][1];
            int cost = fares[i][2];
            minDist[n1][n2] = cost;
            minDist[n2][n1] = cost;
            mp.get(n1).add(n2);
            mp.get(n2).add(n1);
        }
        
        // 1부터 n까지 노드에서 모든 점까지 최소 거리 구하기
        setMinDist(s, minDist, mp);
        setMinDist(a, minDist, mp);
        setMinDist(b, minDist, mp);
        
        // S->i i->a i->b가 최소인 값 구하기
        for(int i=1; i<n+1; i++){
            int dist = minDist[s][i];
            dist += minDist[a][i];
            dist += minDist[b][i];
            answer = Integer.min(answer, dist);
        }
        
        return answer;
    }
    
    // i에서 갈 수 있는 모든 점의 최소 거리 저장하기
    private void setMinDist(int i, int[][] minDist, List<List<Integer>> mp){
        PriorityQueue<Integer> qu = new PriorityQueue<>((a,b)->minDist[i][a] - minDist[i][b]);
        
        // 자기 자신은 거리가 0
        minDist[i][i] = 0;
        // i 인접 경로 저장!
        for(int nxtN : mp.get(i)){
            qu.add(nxtN);
        }
        
        while(!qu.isEmpty()){
            int n = qu.poll();
            // System.out.println("n->"+n);
            // n 인점 노드 조회하기
            for(int nxtN : mp.get(n)){
                // n과 바로 옆 거리
                int distN = minDist[n][nxtN];
                
                // i부터 n까지 최소거리
                int nxtDist = minDist[i][n];
                
                // i -> n -> nxt가 i->nxt를 위한 최소 경로!
                if(nxtDist + distN < minDist[i][nxtN]){
                    // System.out.println("  nxtN->"+nxtN+"  sum->"+(nxtDist + distN)+" "+minDist[i][nxtN]);
                    minDist[i][nxtN] = nxtDist + distN;
                    qu.add(nxtN);
                }
            }
        }
        
    }
}