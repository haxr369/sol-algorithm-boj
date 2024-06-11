/**
-1, +1, -10, +10, -100, +100 등과 같이 절댓값이 10c (c ≥ 0 인 정수) 형태인 정수
엘리베이터가 위치해 있는 층과 버튼의 값을 더한 결과가 0보다 작으면 엘리베이터는 움직이지 않습니다. 

버튼 한 번당 마법의 돌 한 개 항상 최소한의 버튼

민수가 어떤 층에서 엘리베이터를 타고 0층으로 내려가는데 필요한 마법의 돌의 최소 개수

**/
import java.util.*;
class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        int[] visited = new int[100000001];
        visited[storey] = 1;
        Queue<Integer> qu = new LinkedList<>();
        qu.add(storey);
        boolean isFind = false;
        int posi = 1; // 1의 자리부터 0으로 만들기 

        while(!qu.isEmpty() && posi <= 100000000){
            
            int size = qu.size();
            // System.out.println("---"+posi+"를 더하기 size : ");
            while(size>0){
                int p = qu.poll();
                // System.out.println("- p "+p+" visited : "+visited[p]);
                if(p%(posi*10) == 0){
                    size--;
                    qu.add(p);
                    continue;
                }
                    
                // 더하거나 빼기
                int addition = posi*10 - p%(posi*10);
                // System.out.println("합 : "+(p+addition)+" 추가 : "+addition+" 맹이 : "+(addition/posi)+" 기준 : "+visited[p+addition]);
                if(p+addition <= 100000000 && 
                    (visited[p+addition]==0 || visited[p+addition] > visited[p]+(addition/posi))){
                    visited[p+addition] = visited[p]+(addition/posi);
                    // System.out.println(" 결과 : "+visited[p+addition]);
                    qu.add(p+addition);
                }
                int subtract = p%(posi*10) ;
                // System.out.println("차 : "+(p-subtract)+" 빼기 : "+subtract+ " 맹이 : "+(subtract/posi)+" 기준 : "+visited[p-subtract]);
                if(p-subtract >=0 && (visited[p-subtract]==0 || visited[p-subtract] > visited[p]+(subtract/posi))){
                    visited[p-subtract] = visited[p]+(subtract/posi);
                    qu.add(p-subtract);
                    // System.out.println(" 결과 : "+visited[p-subtract]);
                    if(p-subtract == 0){
                        isFind = true;
                    }
                }
                size--;
            }
            posi*=10;

        }
        
        return visited[0]-1;
    }
}