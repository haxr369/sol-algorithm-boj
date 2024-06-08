/**

1. 처음 n/3장 뽑기
2. 모든 카드 댁을 쓸 때까지 반복
    두장을 뽑아서 선택
        a. 안 가져가냐
        b. 하나만 가져가냐
        c. 두개 다 가져가냐
    가진 카드 중 두장의 합이 n+1일 때
        다음 라운드 진행
    else
        라운드 종료

카드는 최대 1000개 500번 뽑는다면 3^500 => dfs 방법은 쓸 수 없다.

[3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4]

손 안에 [ 3 6 7 2 ]

R1 1 10 
        10 => coin 1개 소모
R2 5 9
        6,7 => coin 0개 소모
R3 8 12
        1, 12 => coin 2개 소모
R4 11 4
        2, 11 => coin 1개 소모
R5 댁 다 사용 끝
    
**/
import java.util.*;
class Solution {
    int n;
    public int solution(int coin, int[] cards) {
        int answer = 1;
        int n=cards.length;
        Set<Integer> handCard = new HashSet<>();
        Set<Integer> keepCard = new HashSet<>();
        for(int i=0; i<n/3; i++){
            handCard.add(cards[i]);
        }
        
        int idx = n/3;
        while(idx < n){ // 모든 댁을 다 사용하면 종료
            
            boolean isFind = false;
            // 댁에서 카드 2개 사용
            // System.out.println("추가 카드 : "+cards[idx]+" , "+cards[idx+1]+" coin : "+coin);
            keepCard.add(cards[idx]);
            keepCard.add(cards[idx+1]);
            idx+=2;
            
            // Coin 0개 사용하는 경우
            for(int c : handCard){
                // 두 수의 합이 n+1인 경우가 존재한다면,
                if(handCard.contains(n+1-c)){
                    handCard.remove(c);
                    handCard.remove(n+1-c);
                    isFind = true;
                    break;
                }
            }
            
            // Coin 1개 사용하는 경우
            if(!isFind && coin > 0){
                for(int c : handCard){
                    if(keepCard.contains(n+1-c)){
                        handCard.remove(c);
                        keepCard.remove(n+1-c);
                        isFind = true;
                        coin--;
                        break;
                    }
                }
            }
            
            // coin 2개 사용하는 경우
            if(!isFind && coin > 1){
                for(int k : keepCard){
                    if(keepCard.contains(n+1-k)){
                        keepCard.remove(k);
                        keepCard.remove(n+1-k);
                        isFind = true;
                        coin-=2;
                        break;
                    }
                }
            }
            
            if(!isFind){
                break;
            }
            answer++;
        }
        return answer;
        
    }
}