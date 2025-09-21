/**
LCM은 모든 n과 나눠 떨어지는 숫자

반복 n : arr
if(LCM%n == 0):
    => 스킵!
else:
    n에는 LCM에는 없는 약수가 존재하는 것.
    1->100까지 LCM과 곱하고 n으로 나눠보기
        => 나눠 떨어지는 첫번째 수를 LCM에 곱해서 출력
**/

class Solution {
    public int solution(int[] arr) {
        int answer = 0;
        int LCM = 1;
        
        for(int i=0; i<arr.length; i++){
            int n = arr[i];
            if(LCM % n == 0){
                // System.out.println("====skip! n->"+n);
                continue;
            }
            int multiple = 2;
            int tmp = LCM;
            while(tmp % n != 0){
                tmp /= (multiple - 1);
                tmp *= multiple;
                multiple += 1;
            }
            LCM = tmp;
            // System.out.println("====LCM! n->"+n+" LCM->"+LCM);
        }
        
        
        return LCM;
    }
}