import java.util.*;
class Solution {
    /**
    백만 정도의 숫자
    
    1~백만개 숫자를 뺄 수 있음..
    
    문자를 큐에 한개 넣고 시작
    큐의 상단 숫자(t)와 i번째 문자를 비교
    t < i라면 t를 큐에서 빼버리기
    다시 상단과 비교 반복..
    t >= i라면 i를 큐에 넣고 다음 i+1 문자 비교
    
    
    **/
    public String solution(String number, int k) {
        String answer = "";
        
        // char num = number.charAt(0);
        int dumpCnt = 0;
        Stack<Character> stk = new Stack<>();
        stk.add(number.charAt(0));
        for(int i=1; i<number.length(); i++){
            
            Character num = number.charAt(i);
            
            while(!stk.isEmpty()){
                Character top = stk.peek();
                // System.out.println("top->"+top+" num->"+num);
                // 버릴 수 있고, 버리는게 좋다면,
                if(top < num && dumpCnt < k){
                    dumpCnt += 1;
                    stk.pop();
                    // System.out.println("===="+top+" 버려");
                } else { // 안버리는게 좋다면, 멈추기  
                    break;
                }
            }
            stk.add(num);
            // System.out.println(stk.toString());
        }
        for(int i=0; i<Integer.min(stk.size(), number.length()-k); i++){
            answer = answer + stk.get(i);
        }
        
        return answer;
    }
}