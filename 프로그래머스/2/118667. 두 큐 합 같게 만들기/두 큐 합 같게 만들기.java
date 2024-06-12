/**
길이가 같은 두 개의 큐 
    작업
        하나의 큐를 골라 원소를 추출(pop)하
        추출된 원소를 다른 큐에 집어넣는(insert) 작업
각 큐의 원소 합이 같도록 만들려고 합니다.

필요한 작업의 최소 횟수
작업이 2n을 넘어가면 불가능하다고 판단할 수 있음
1. qu1, qu2 요소의 합 구하기 , s1, s2
s1과 s2가 같아질 때까지
    s1이 s2 보다 더 크면
        s1의 front를 빼서
        s1 -= front
        s2 += front
        qu2.add(front)
    s2이 s1 보다 더 크면
        s2의 front를 빼서
        s2 -= front
        s1 += front
        qu1.add(front)

cnt = 1

s1 = 13
s2 = 13
5 3 2 3 
1 2 1 2 3 4 



**/
import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        int n = queue1.length;
        long sum1 = 0;
        Queue<Integer> qu1 = new LinkedList<>();
        for(int i=0; i<queue1.length; i++){
            sum1 += queue1[i];
            qu1.add(queue1[i]);
        }
        
        long sum2 = 0;
        Queue<Integer> qu2 = new LinkedList<>();
        for(int i=0; i<queue2.length; i++){
            sum2 += queue2[i];
            qu2.add(queue2[i]);
        }
        
        while(answer <= 2*n+1 && sum1 != sum2){
            if(sum1 < sum2){
                int p = qu2.poll();
                qu1.add(p);
                sum1+=p;
                sum2-=p;
            } else{
                int p = qu1.poll();
                qu2.add(p);
                sum2+=p;
                sum1-=p;
            }
            answer++;
        }
        if(sum1 != sum2){
            return -1;
        } else
            return answer;
    }
}