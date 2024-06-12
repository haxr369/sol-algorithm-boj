/**
병사 n명 enemy[i]명
    병사 enemy[i]명으로 막기
    
남은 병사의 수보다 현재 라운드의 적의 수가 더 많으면 게임이 종료
무적권은 최대 k번 사용 -> 최대 k번 라운드 스킵 가능

최대한 많은 라운드

최대 백만라운드 진행
    -> k는 최대 50만 회
    
D[i][k] 은 i번째 라운드에서 무적권을 k번 사용한 병사의 최대 값

example
i => (0, I)까지 최대 값 k를 제외한 합이 n 이하라면 준호는 I번째 라운드까지 도달할 수 있다.

k부터 범위를 늘려가면서 범위의 수 I개 중 작은 (I-k)개 수의 합을 체크할 수 있으면 된다.
    최소값 우선순위 큐에 enemy를 하나씩 저장 
        큐의 크기는 k
        
        
round = 0;
sum=0
sum이 n 이하 일 경우
1. top 보다 enemy가 크거나 같으면
    1. 큐의 크기가 k 미만일 경우
            enemy를 큐에 넣기
    2. 큐의 크기가 k 이상일 경우
            top을 큐에서 빼고
            sum에 더하기
            enemy를 큐에 넣기
2. top 보다 enemy가 작으면
    1. 큐의 크기가 k 미만일 경우
            enemy를 큐에 넣기
    2. 큐의 크기가 k 이상일 경우
            sum에 enemy 더하기
round ++;
**/
import java.util.*;
class Solution {
    public int solution(int n, int k, int[] enemy) {
        int round = 0;
        int sum = 0;
        boolean fill = false;
        // 크기가 k개로 제한된 큐
        Queue<Integer> qu = new PriorityQueue<>(k);
        while(sum <= n && round<enemy.length){
            // System.out.println("round : "+round +" sum : "+sum);
            if(qu.size() < k){
                qu.add(enemy[round]);
            } else{ // 사이즈가 k만할 때
                int top = qu.peek();
                if(top < enemy[round]){ // 더 큰 값을 큐에 넣자
                    // System.out.println("-- "+top+" 넣자");
                    sum += top;
                    qu.poll(); // top 빼기
                    qu.add(enemy[round]); // 더 큰 값 넣기
                    if(sum > n)
                        fill = true;
                } else{
                    // System.out.println("-- "+enemy[round]+" 넣자");
                    sum += enemy[round];
                    if(sum > n)
                        fill = true;
                }
            }
            round++;
        }
        if(fill)
            round--;
        
        return round;
    }
}