/**
시소 짝꿍인 두 사람은 토크의 크기가 같은 두 사람
"무게 x 거리"가 같은 사람

사람은 100,000명
1대 1 매칭은 불가하다.

시소 거리는 2,3,4
100 ≤ weights[i] ≤ 1,000

acc는 100부터 1000까지 무게의 사람이 몇 명있나 누적합

1번부터 순회 => i
    최대 값 보다 weights[i]*2가 더 크면 
        순회 종료
        
    weights[i]*2, *3, *4가 있는지 확인

**/

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        int[] acc = new int[1004];
        
        for(int w=0; w<weights.length; w++){
            acc[weights[w]]++;
        }
        // {2,2}, {2,3}, {2,4} => {1,1}, {2,3}, {1,2}
        // {3,3}, {3,4} => {3,4}
        // {4,4}
        // {1,1}, {3,4}, {2,3}, {1,2}
        // 100,000* 100,000 == 10,000,000,000
        
        // acc[1000]=100000;
        
        for(int w=100; w<=1000; w++){
            if(acc[w]==0) continue;
            // System.out.println("w : "+w);
            if(acc[w]>1){ // 1:1
                // System.out.println("w : "+w+" acc : "+(acc[w]*(acc[w]-1)/2));
                answer += (long)acc[w]*(acc[w]-1)/2;
            }
            if(w%2==0 && w*3/2 <= 1000 && acc[w*3/2] > 0){ // 2:3
                // System.out.println("w : "+w+" acc : "+(acc[w]*acc[w*3/2]));
                answer += (long)acc[w]*acc[w*3/2];
            }
            if(w%3==0 && w*4/3 <= 1000 && acc[w*4/3] > 0){ // 3:4
                // System.out.println("w : "+w+" acc : "+(acc[w]*acc[w*4/3]));
                answer += (long)acc[w]*acc[w*4/3];
            }
            if(w*2<=1000 && acc[w*2] > 0){ // 1:2
                // System.out.println("w : "+w+" acc : "+(acc[w]*acc[w*2]));
                answer += (long)acc[w]*acc[w*2];
            }
        }
        System.out.println(answer);
        return answer;
    }
}