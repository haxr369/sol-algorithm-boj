import java.util.Arrays;
import java.util.Collections;

class Solution
{
    public int solution(int []A, int []B)
    {
        int answer = 0;
        int N = A.length;
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        // System.out.println("Hello Java");
        Arrays.sort(A);
        Arrays.sort(B);
      
        
        for(int i=0; i<N; i++){
            int temp = A[i]*B[N-1-i];
            answer += temp;
        }
        
        

        return answer;
    }
}