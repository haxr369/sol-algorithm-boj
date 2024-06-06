/**

각 면이 나올 확률은 동일
주사위에 쓰인 수의 구성은 모두 다름
각각 가져간 주사위를 모두 굴린 뒤, 나온 수들을 모두 합해 점수를 계산

A는 자신이 승리할 확률이 가장 높아지도록 주사위를 가져가려 합니다.

10C5 = 252개 뽑는 개수

6^10*252 ~= 100억 => 브루트 포스는 불가능하다.

A가 가장 많이 승리하는 주사위 조합

6^5 = 7776 A와 B의 승,무,패를 계산하려면
7776개 수와 7776를 1대1 비교 => 7776 x 7776
하지만, 정렬되어 있다면, 7776 전부와 비교할 필요 없다. 
앞에서 A의 승이 확정된다면, A의 다른 수와 비교할 필요 없기 때문이다. 

**/
import java.util.*;
class Solution {
    // 주사위 개수
    int N;
    int[] dices;
    int[][] d;
    int maxCnt = 0;
    
    public int[] solution(int[][] dice) {
        d = dice;
        N = dice.length; 
        dices = new int[N/2];
        int[] maxDices = new int[N/2];
        for(int i=0; i<N/2; i++){
            dices[i] = i;
        }
        // List<Integer> bSumbs = new ArrayList<>();
        // addSumb(bSumbs, dices, 0,0);
        // for(int i : bSumbs){
        //     System.out.println(i);
        // }
        dfs(-1, 0, maxDices);
        for(int i=0; i<maxDices.length; i++){
            maxDices[i]++;
        }
        return maxDices;
    }
    
    private void dfs(int idx, int cnt, int[] maxDices){
        if(cnt == N/2){
            int ret = checkDices();
            // System.out.println("max : "+maxCnt+" ret : "+ret);
            if(maxCnt < ret){
                maxCnt = ret;
                for(int i=0; i<dices.length; i++){
                    maxDices[i] = dices[i];
                }
            }
            return ;
        }
        // N이 10이고 cnt가 4일 때, (N/2+1+cnt) = 10
        // N이 2이고 cnt가 0일 때,              = 2
        for(int i=idx+1; i<(N/2+1+cnt); i++){
            dices[cnt] = i;
            dfs(i, cnt+1, maxDices);
            dices[cnt] = 0;
        }
    }
    
    private int checkDices(){
        // A가 가질 수 있는 모든 합의 값들과
        // B가 가질 수 있는 모든 합의 값들을 구하기
        // System.out.print("---새로운 경우! ---");
        // for(int i=0; i<N/2; i++){
        //     System.out.print(dices[i]);
        // }
        // System.out.print('\n');
        List<Integer> aSumbs = new ArrayList<>();
        addSumb(aSumbs, dices,0,0);
        Collections.sort(aSumbs);
        
//         for(int i : aSumbs){
//             System.out.print(i+" ");
//         }
//         System.out.println();
        
        int idx = 0;
        int[] bDices = new int[N/2];
        for(int i=0; i<N; i++){
            boolean canUse = true;
            for(int j=0; j<N/2; j++){
                if(dices[j]==i)
                    canUse = false;
            }
            if(canUse){
                // System.out.println(i+"를 사용할 수 있다.");
                bDices[idx] = i;
                idx++;
            }
        }

        
        List<Integer> bSumbs = new ArrayList<>();
        addSumb(bSumbs, bDices, 0,0);
        Collections.sort(bSumbs);
        
        // for(int i : bSumbs){
        //     System.out.print(i+" ");
        // }
        // System.out.println();
        
        
        int aIdx = 0;
        int bIdx = 0;
        int cntArray = 0; // 누적합
        int eqCnt = 0;
        while(bIdx < bSumbs.size()){
            
            if(bSumbs.get(bIdx) >= aSumbs.get(aIdx)){
                // System.out.println("a : "+aSumbs.get(aIdx)+" b : "+bSumbs.get(bIdx)+" bIdx : "+bIdx);
                cntArray += bIdx;
                aIdx++;
            } else{
                bIdx++;
            }
            if(aIdx==aSumbs.size())
                break;
        }
        // 나머지 a의 값들 넣어주기
        while(aIdx < aSumbs.size()){
            cntArray += bSumbs.size();
            aIdx++;
        }
        // System.out.println("A가 이긴 수 : "+cntArray);
        return cntArray;
    }
    
    private void addSumb(List<Integer> sumbs, int[] dice, int cnt, int sum){
        if(cnt==N/2){
            sumbs.add(sum);
            return;
        }
        
        for(int i=0; i<6; i++){
            sum += d[dice[cnt]][i];
            addSumb(sumbs, dice, cnt+1, sum);
            sum -= d[dice[cnt]][i];
        }
        
    }
    
    
}