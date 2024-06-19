/**
철수꺼 다 나누고, 영희꺼 하나도 못 나눔
영희꺼 다 나누고, 철수꺼 하나도 못 나눔

이런 수 중 최대 값.

철수 숫자 - 영희 숫자 1대1 비교라면 5x10^5 x 5x10^5 = 25ㅌ10^10 => 무조건 시간초과

철수 숫자들 중 공통 약수가 있다면?
누적해서 약수를 찾을 수 있다면?

철수 카드 : [6,12,30]
최대 공약수를 구하면 그 수의 약수는 다 후보가 될 수 있다.
최대 공약수의 약수가 영희 카드에 있는지 체크!!


**/
import java.util.*;
class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        
        // System.out.println("gcd 12, 45 "+getGCD(12,45));
        
        int aGCD = getMaxGCD(arrayA);
        int bGCD = getMaxGCD(arrayB);
        Set<Integer> aSet = new HashSet<>();
        
        Set<Integer> bSet = new HashSet<>();
        for(int i=0; i<arrayB.length; i++){
            bSet.add(arrayB[i]);
        }
        // System.out.println("a : "+aGCD +" b : "+bGCD);
        List<Integer> aDivisor = getDivisor(aGCD);
        boolean isFind = true;
        for(int i : aDivisor){ // 철수의 약수가 영희의 숫자를 나누지 못하는 값 찾기
            // System.out.print(i+" ");
            isFind = true;
            for(int j=0; j<arrayB.length; j++){
                if(arrayB[j]%i == 0){
                    isFind = false;
                    break;
                }
            }if(isFind){
                answer = Math.max(answer, i);
                break;
            }
        }
        // System.out.print("\n");
        List<Integer> bDivisor = getDivisor(bGCD);
        for(int i : bDivisor){
            // System.out.print(i+" ");
            isFind = true;
            for(int j=0; j<arrayA.length; j++){
                if(arrayA[j]%i == 0){
                    isFind = false;
                    break;
                }
            }if(isFind){
                answer = Math.max(answer, i);
                break;
            }
        }
        // System.out.print("\n");
        
        return answer;
    }
    private int getMaxGCD(int[] arrayA){
        int aGCD ;
        if(arrayA.length == 1){
            aGCD = arrayA[0];
        } else{
            aGCD = getGCD(arrayA[0], arrayA[1]);
        }
        
        for(int i=3; i<arrayA.length; i++){
            aGCD = getGCD(aGCD, arrayA[i]);
        }
        return aGCD;
    }
    private int getGCD(int a, int b){
        int high = Math.max(a,b); // 45
        int low = Math.min(a,b); // 12
        int reminder = high%low; // 9
        while(reminder != 0){
            high = low; 
            low = reminder;  
            reminder = high%low;
        }
        return low;
    }
    
    // 정수 d의 약수들을 찾기
    private List<Integer> getDivisor(int d){
        List<Integer> dDivisor = new ArrayList<>();
        dDivisor.add(d);
        int n=2;
        while(n*2 <= d){
            if(d%n==0)
                dDivisor.add(d/n);
            n++;
        }
        return dDivisor;
    }
}