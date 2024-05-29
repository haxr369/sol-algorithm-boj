/**
각 곡괭이는 광물 5개를 캔 후 더 이상 사용할 수 없음

1. 한번 사용한 곡괭이는 계속 사용
2. 광물은 주어진 순서대로만 캘 수 있음
3. 모든 광물을 캐거나, 더 이상 사용할 곡괭이가 없을 때가지 캐기

작업 끝낼 때까지 최소 피로도 구하기

곡괭이 수는 최대 15개
광물 수는 최대 50개

dfs로 각 곡괭이를 사용하는 경우의 수를 구하지만
선택하는 순서에 따라 피로도가 달라진다
D[n][d][i][s]를 최소로 유지하면서 지나간 것을 다시 지나지 않는 DP를 이용하자.

**/

import java.util.*;
class Solution {
    
    // D[n][d][i][s]를 1 이상의 최소 값을 유지
    private int[][][][] D = new int[52][6][6][6];
    private int answer = Integer.MAX_VALUE;
    public int solution(int[] picks, String[] minerals) {
        
        int[] used = {0,0,0};
        dfs(0, used, picks, minerals);

        return answer;
    }
    
    // n~ n+4까지 미네랄을 캐기
    private void dfs(int n, int[] used, int[] picks, String[] minerals){
        int beforeStress;
        if(n==0){
            beforeStress = 0;
        } else
            beforeStress = D[n-1][used[0]][used[1]][used[2]];
        // System.out.println((5*n)+"에서 "+(5*n+4)+"번째 미네랄 추출 예정! 누적 스트래스 : "+beforeStress);
        // System.out.println(used[0]+" : "+used[1]+" : "+used[2]);
        
        // 앞선 재귀함수에서 모든 미네랄에 대해 스트래스를 측정했을 경우.
        if(5*n >= minerals.length){
            answer = Math.min(D[n-1][used[0]][used[1]][used[2]], answer);
            // System.out.println("---최소 값 : "+answer);
            return;
        }
        
        // 모든 광물을 캐지 않았지만 곡괭이를 더 이상 사용하지 못하는 경우
        if(n == Arrays.stream(picks).sum()){
            answer = Math.min(D[n-1][used[0]][used[1]][used[2]], answer);
            // System.out.println("---최소 값 : "+answer);
            return;
        }
        
        
        // picks[p] 보다 크지 않도록 
        for(int p=0; p<3; p++){
            if(used[p] == picks[p]) continue;
            used[p]++;
            // n, d, i, s | p인 것에 +1
            // n~n+4까지 미네랄 캐기
            int stress = 0;
            int m=0;
            while(m<5 && (5*n+m<minerals.length)){
                // System.out.println((5*n+m)+"번째 광물 "+minerals[5*n+m]+" p : "+p+"  stress : "+stress);
                // System.out.println(minerals[5*n+m].equals("diamond"));
                if(minerals[5*n+m].equals("diamond")){
                    if(p==0) stress+=1;
                    else if(p==1) stress+=5;
                    else stress+=25;
                } else if(minerals[5*n+m].equals("iron")){
                    if(p==2) stress+=5;
                    else stress+=1;
                } else{
                    stress+=1;
                }
                m++;
            }
            // System.out.println("----stress : "+stress);
            int temp = D[n][used[0]][used[1]][used[2]];
            if(temp==0 || temp > beforeStress+stress){
                D[n][used[0]][used[1]][used[2]] = beforeStress+stress;
                dfs(n+1, used, picks, minerals);
            }
            used[p]--;
        }
        
        
    }
}