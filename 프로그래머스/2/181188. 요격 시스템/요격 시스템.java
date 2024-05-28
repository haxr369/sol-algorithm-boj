/**
미사일을 최소로 사용해서 모든 미사일 요격하기

미사일은 x축에 평행한 직선 형태

미사일 수는 최대 50만개
범위는 0~10억 단순 누적합 불가

s를 기준으로 미사일을 정렬

**/
import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        
        Arrays.sort(targets, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int [] b){
                return a[0] - b[0];
            }
        });
        
        // 미사일 단위로 구간을 만들기
        // SRange부터 eRange의 개행 범위
        // 미사일 (1,5) 일 때 eR=1이라면 새로운 범위로 만들어야함.
        int sR = -1;
        int eR = -1;
        int answer = 0;
        for(int i=0; i<targets.length; i++){
            // System.out.println("sR : "+sR+" eR : "+eR);
            // System.out.println("--s : "+targets[i][0]+" e : "+targets[i][1]);
            if(targets[i][0] >= eR){ // 뉴 범위
                // System.out.println("뉴범위!!");
                answer++;
                sR = targets[i][0];
                eR = targets[i][1];
            } else if(targets[i][0] > sR && targets[i][1] < eR){ // 내부 범위
                sR = targets[i][0];
                eR = targets[i][1];
            } else if(targets[i][0] > sR){
                sR = targets[i][0];
            } else if(targets[i][1] < eR){
                eR = targets[i][1];
            }
        }
        return answer;
    }
}