/**
. 단, 주어진 구간의 시작점이 끝점보다 커서 유효하지 않은 구간이 주어질 수 있으며 이때의 정적분 결과는 -1로

초항이 k인 수열을 전부 구하기 -> List
각 수열의 위치에 따라 누적합 구하기

[a, b] 일때는
x = a, x = b, y = 0 으로 둘러쌓인 공간의 면적

 0 이상의 수 b에 대해 [a, -b]에 대한 정적분 결과는 
 x = a, x = n - b, y = 0 으로 둘러 쌓인 공간의 면적으로 정의하며
 
 정의역이 0~5일 때, a=1, b=-2라면
 정적분 범위는 1~(5-2) => 1~3.
**/

import java.util.*;
class Solution {
    public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];
        
        List<Double> integerate = getIntegral(k);

        for(int i=0; i<ranges.length; i++){
            if(integerate.size() - 1 + ranges[i][1] < ranges[i][0]){
                answer[i] = -1.0;
            } else
                answer[i] = (integerate.get(integerate.size() - 1 + ranges[i][1]) - integerate.get(ranges[i][0]));
        }
        return answer;
    }
    
    private List<Double> getIntegral(int k){
        List<Double> ret = new ArrayList<>();
        ret.add(0.0);
        
        while(k!=1){
            int temp = 0;
            if(k%2==0){
                temp = k/2;
            } else{
                temp = k*3 + 1;
            }
            Double lastArea = ret.get(ret.size()-1);
            ret.add(lastArea + getArea(k, temp));
            // System.out.println("왼쪽 : "+k+ " 오른쪽 : "+temp);
            k = temp;
        }
        return ret;
    }
    
    private Double getArea(int k, int temp){
        return ((double)k + (double)temp)/2;
    }
}