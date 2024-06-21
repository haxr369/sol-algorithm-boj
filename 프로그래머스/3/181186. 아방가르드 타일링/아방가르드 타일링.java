/**
n이 3에 나눠 떨어진다면 
    n만큼 연관된 경우가 2*2
아니라면
    n만큼 연관된 경우가 2
    
D[1] = 1;
D[2] = 3;
D[3] = 10; // 2*D[1]*D[2] - 1(세로로 기둥 세우는 경우 중복 제거) + 2*2 + 1(가로로 긴 기둥 3개 쌓는 경우)

중복되는 부분을 제거해줘야한다.
n을 전부 사용하는 개수 -> A
n을 만드는 개수 -> D

D[5]을 구하기 위해 n을 2와 3으로 쪼개면 3 안에 2의 경우의 수가 들어 있다.
            따라서 3과 2로 나눌 때는 3을 전부 사용하는 경우의 수만 곱해줘야한다.
     
점화식
D[n] = sum(i = 0->4)(A[n-i]*D[i]);

(A+B) mod B == A mod B

**/

class Solution {
    public int solution(int n) {
    
        
        int answer = 0;

        long[] D = new long[100004];
        D[1] = 1; D[2] = 3; D[3] = 10; D[4]=23; D[5] = 62; D[6] = 170;
        
        // 100,000 ^ 2 100억???
        for(int i=7; i<=n; i++){ 
            
            D[i] = D[i-1] + 2*D[i-2]+ 6*D[i-3] + D[i-4] - D[i-6];
            if(D[i] < 0)
                D[i] += 1000000007;
            D[i] = getMod(D[i]);
        }
        return (int)D[n];
    }

    private long getMod(long a){
        return (a)%1000000007;
    }
}

        // 최대 100억회...
        // for(int i=4; i<=n; i++){ // 시간 초과 나는 로직..
        //     D[i] = 0;
        //     for(int j=1; j<i; j++){
        //         if(i%2 == 0 && j == (i/2)){
        //             D[i] += getMod(A[j]*D[j]); // D[i]*D[i]
        //         } else{
        //             D[i] += getMod(D[j]*A[i-j]); // D[j]*A[i-j];
        //         }
        //         D[i] = getMod(D[i]);
        //     }
        //     if(i%3 == 0){
        //         A[i] = 4;
        //         D[i] += 4;
        //     } else{
        //         A[i] = 2;
        //         D[i] += 2;
        //     }
        //     D[i] = getMod(D[i]);
        // }