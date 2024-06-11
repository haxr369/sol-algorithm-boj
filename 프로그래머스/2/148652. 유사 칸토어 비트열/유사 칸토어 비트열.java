/**

0 번째 유사 칸토어 비트열은 "1" 입니다.
n(1 ≤ n) 번째 유사 칸토어 비트열은 
    n - 1 번째 유사 칸토어 비트열에서의 
        1을 11011로 치환하고 
        0을 00000로 치환하여 만듭니다.

n=1 => 5
11011

n=2 => 25
110  11110110000011 01111011

l-1 -> r-1로 i가 가면서
    i%5 ==2 || i%5^2 ==2|| i%5^3 ==2 ....
    이러면 스킵
**/

class Solution {
    public int solution(int n, long l, long r) {
        int answer = 0;
        
        for(long i=l-1; i<= r-1; i++){
            int cnt = 0;
            long numb = i;
            boolean ok = true;
            while(cnt < n && numb > 0){
                if(numb%5 == 2){
                    ok = false;
                    break;
                }
                numb/=5;
                cnt++;
            }
            if(ok){
                answer++;
            }
        }
        
        return answer;
    }

}