/**
 롤케이크 위에 올려진 토핑들의 종류
 각 조각에 동일한 가짓수의 토핑이 올라가면 공평하게 롤케이크가 나누어진 것
 롤케이크를 공평하게 자르는 방법의 수
 
 누적합
 
 topping 원소는 10000 -> 각 원소의 개수를 배열에 저장
 0번째 부터 i번째까지 가지수를 누적하기
 
 [1, 2, 1, 3, 1, 4, 1, 2]
뒤 부터 [4, 4, 4, 4, 3, 3, 2, 1]  
앞 부터 [1, 2, 2, 3, 3, 4, 4, 4] 
두 개가 같을 때가 받

                 
 [1, 2, 2, 3] [1, 2, 2, 3]
 
**/
import java.util.*;
class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        Set<Integer> fset = new HashSet<>();
        Set<Integer> bset = new HashSet<>();
        int[] frontIntegral = new int[topping.length];
        int[] behindIntegral = new int[topping.length];
        for(int i=0; i<topping.length; i++){
            fset.add(topping[i]);
            bset.add(topping[topping.length-1-i]);
            frontIntegral[i] = fset.size();
            behindIntegral[topping.length-1-i] = bset.size();
        }
        
        for(int i=0; i<topping.length-1; i++){
            if(frontIntegral[i] == behindIntegral[i+1]){
                answer++;
            }
        }
    
        return answer;
    }
}