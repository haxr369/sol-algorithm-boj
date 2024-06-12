/**
귤을 크기별로 분류했을 때 서로 다른 종류의 수를 최소화
경화가 귤 k개를 고를 때 크기가 서로 다른 종류의 수의 최솟값

귤 크기 배열 저장
내림차순으로 정렬



**/
import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        Integer[] sizes = new Integer[10000004];
        Arrays.fill(sizes, 0);
        for(int i=0; i<tangerine.length; i++){
            sizes[tangerine[i]]++;
        }
        // Arrays.sort(sizes, Collections.reverseOrder());
        Arrays.sort(sizes, (a,b)->{
            return b-a;
        });
        // 큰거부터 빼기 -> 그리디
        for(int i=0; i<sizes.length; i++){
            if(sizes[i] <= k){
                answer++;
                k-=sizes[i];
                // System.out.println("추가 : "+sizes[i] +" 그래서 : "+k);
                if(k==0)
                    break;
            } else{
                answer++;
                break;
            }
        }
        /**
            D[k] k개를 만들기 위해 필요한 종류의 개수
            D[k-a] = D[k]+1;
            D[0]을 출력
            
            k
        **/
        
        
        return answer;
    }
}