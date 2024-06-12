/**
answer =0

x를 0부터 d까지
    (d*d - (x*x) )/ k*K = A
    answer += (int) A의 제곱근 +1
**/
import java.util.*;
class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        
        // x축 이동
        for(int x=0; x<=d; x+=k){
            // System.out.println("x : "+x);
            long A = ((long)d*d - (long)x*x)/((long)k*k);
            // System.out.println("A : "+A+" a : "+((int) Math.sqrt(A)));
            answer += (long) Math.sqrt(A) + 1;
        }
        return answer;
    }
}